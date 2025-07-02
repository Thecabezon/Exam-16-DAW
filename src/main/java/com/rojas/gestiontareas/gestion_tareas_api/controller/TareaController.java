package com.rojas.gestiontareas.gestion_tareas_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rojas.gestiontareas.gestion_tareas_api.entity.Tarea;
import com.rojas.gestiontareas.gestion_tareas_api.service.TareaService;
import com.rojas.gestiontareas.gestion_tareas_api.dto.CrearTareaDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import com.rojas.gestiontareas.gestion_tareas_api.dto.ActualizarTareaDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public List<Tarea> listarTareas() {
        return tareaService.obtenerTodasLasTareas();
    }

    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@Valid @RequestBody CrearTareaDTO tareaDTO) {
        Tarea tareaCreada = tareaService.crearTarea(tareaDTO);
        return new ResponseEntity<>(tareaCreada, HttpStatus.CREATED);
    }

    // GET http://localhost:8086/api/tareas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerTareaPorId(@PathVariable Long id) {
        Tarea tarea = tareaService.obtenerTareaPorId(id);
        return ResponseEntity.ok(tarea);
    }

    // PUT http://localhost:8086/api/tareas/1
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id,
            @Valid @RequestBody ActualizarTareaDTO tareaDTO) {
        Tarea tareaActualizada = tareaService.actualizarTarea(id, tareaDTO);
        return ResponseEntity.ok(tareaActualizada);
    }

    // DELETE http://localhost:8086/api/tareas/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        tareaService.eliminarTarea(id);
        return ResponseEntity.noContent().build();
    }

}