package com.rojas.gestiontareas.gestion_tareas_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rojas.gestiontareas.gestion_tareas_api.dto.ActualizarTareaDTO;
import com.rojas.gestiontareas.gestion_tareas_api.dto.CrearTareaDTO;
import com.rojas.gestiontareas.gestion_tareas_api.entity.Tarea;
import com.rojas.gestiontareas.gestion_tareas_api.service.TareaService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TareaWebController {

    @Autowired
    private TareaService tareaService;

    @GetMapping("/")
    public String index() {
        return "redirect:/tareas";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/tareas")
    public String listaTareas(Model model) {
        List<Tarea> tareas = tareaService.obtenerTodasLasTareas();

        model.addAttribute("tareas", tareas);

        return "tareas";
    }

    // --- MÉTODOS PARA CREAR TAREAS ---
    @GetMapping("/tareas/nueva")
    public String mostrarFormularioDeNuevaTarea(Model model) {
        model.addAttribute("tarea", new Tarea());
        return "tarea-form";
    }

    // Procesa el envío del formulario de nueva tarea
    @PostMapping("/tareas/nueva")
    public String guardarNuevaTarea(@ModelAttribute("tarea") Tarea tarea) {
        tarea.setCompletada(false);
        CrearTareaDTO dto = new CrearTareaDTO();
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        tareaService.crearTarea(dto);

        return "redirect:/tareas";
    }

    // --- MÉTODOS PARA EDITAR TAREAS ---
    @GetMapping("/tareas/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable Long id, Model model) {
        Tarea tarea = tareaService.obtenerTareaPorId(id);
        model.addAttribute("tarea", tarea);
        return "tarea-form";
    }

    // Procesa el envío del formulario de edición
    @PostMapping("/tareas/editar/{id}")
    public String actualizarTarea(@PathVariable Long id, @ModelAttribute("tarea") Tarea tarea) {
        ActualizarTareaDTO dto = new ActualizarTareaDTO();
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setCompletada(tarea.isCompletada());
        tareaService.actualizarTarea(id, dto);

        return "redirect:/tareas";
    }

    // --- MÉTODO PARA ELIMINAR TAREAS ---
    @GetMapping("/tareas/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        tareaService.eliminarTarea(id);
        return "redirect:/tareas";
    }

}