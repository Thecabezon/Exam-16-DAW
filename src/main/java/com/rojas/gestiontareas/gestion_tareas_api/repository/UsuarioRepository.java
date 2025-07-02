package com.rojas.gestiontareas.gestion_tareas_api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rojas.gestiontareas.gestion_tareas_api.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}