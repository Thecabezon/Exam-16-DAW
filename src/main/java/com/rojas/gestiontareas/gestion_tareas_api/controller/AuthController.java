package com.rojas.gestiontareas.gestion_tareas_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rojas.gestiontareas.gestion_tareas_api.dto.LoginDTO;
import com.rojas.gestiontareas.gestion_tareas_api.dto.RegistroDTO;
import com.rojas.gestiontareas.gestion_tareas_api.dto.TokenDTO;
import com.rojas.gestiontareas.gestion_tareas_api.entity.Rol;
import com.rojas.gestiontareas.gestion_tareas_api.entity.Usuario;
import com.rojas.gestiontareas.gestion_tareas_api.repository.RolRepository;
import com.rojas.gestiontareas.gestion_tareas_api.repository.UsuarioRepository;
import com.rojas.gestiontareas.gestion_tareas_api.security.JwtProvider;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> authenticateUser(@RequestBody LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = jwtProvider.generateToken(authentication);
        
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistroDTO registroDto) {
        if (usuarioRepository.existsByUsername(registroDto.getUsername())) {
            return new ResponseEntity<>("El nombre de usuario ya está en uso.", HttpStatus.BAD_REQUEST);
        }

        if (usuarioRepository.existsByEmail(registroDto.getEmail())) {
            return new ResponseEntity<>("El email ya está en uso.", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(registroDto.getNombre());
        usuario.setUsername(registroDto.getUsername());
        usuario.setEmail(registroDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));

        Rol rol = rolRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        usuario.setRoles(Collections.singleton(rol));

        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Usuario registrado exitosamente.", HttpStatus.OK);
    }
}