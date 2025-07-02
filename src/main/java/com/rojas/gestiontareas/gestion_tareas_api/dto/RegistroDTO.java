package com.rojas.gestiontareas.gestion_tareas_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistroDTO {
    @NotEmpty @Size(min = 3, max = 50)
    private String nombre;

    @NotEmpty @Size(min = 3, max = 50)
    private String username;
    
    @NotEmpty @Email
    private String email;

    @NotEmpty @Size(min = 6, max = 20)
    private String password;
}