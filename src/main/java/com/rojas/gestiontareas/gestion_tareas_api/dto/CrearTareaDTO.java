package com.rojas.gestiontareas.gestion_tareas_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CrearTareaDTO {

    @NotEmpty(message = "El título no puede estar vacío.")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres.")
    private String titulo;

    @NotEmpty(message = "La descripción no puede estar vacía.")
    private String descripcion;
    
}