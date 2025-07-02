package com.rojas.gestiontareas.gestion_tareas_api.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.rojas.gestiontareas.gestion_tareas_api.dto.CrearTareaDTO;
import com.rojas.gestiontareas.gestion_tareas_api.entity.Tarea;
import com.rojas.gestiontareas.gestion_tareas_api.repository.TareaRepository;
import com.rojas.gestiontareas.gestion_tareas_api.dto.ActualizarTareaDTO;
import com.rojas.gestiontareas.gestion_tareas_api.exception.ResourceNotFoundException;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    /**
     * @return
     */
    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepository.findAll();
    }

    /**
     * Crea una nueva tarea en la base de datos.
     * 
     * @param tareaDTO Los datos de la tarea a crear.
     * @return La tarea guardada con su ID y fecha de creación.
     */

    public Tarea crearTarea(CrearTareaDTO tareaDTO) {
        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setTitulo(tareaDTO.getTitulo());
        nuevaTarea.setDescripcion(tareaDTO.getDescripcion());
        nuevaTarea.setCompletada(false);
        return tareaRepository.save(nuevaTarea);
    }

    /**
     * Obtiene una tarea por su ID.
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    public Tarea obtenerTareaPorId(Long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la tarea con el ID: " + id));
    }

    /**
     * Actualiza una tarea existente.
     * @param id
     * @param tareaDTO
     * @return
     * @throws ResourceNotFoundException
     */
    public Tarea actualizarTarea(Long id, ActualizarTareaDTO tareaDTO) {
        Tarea tareaExistente = obtenerTareaPorId(id);

        tareaExistente.setTitulo(tareaDTO.getTitulo());
        tareaExistente.setDescripcion(tareaDTO.getDescripcion());
        tareaExistente.setCompletada(tareaDTO.isCompletada());

        return tareaRepository.save(tareaExistente);
    }

    /**
     * Elimina una tarea por su ID.
     * @param id
     * @throws ResourceNotFoundException
     */
    public void eliminarTarea(Long id) {
        Tarea tareaExistente = obtenerTareaPorId(id);
        tareaRepository.delete(tareaExistente);
    }

}