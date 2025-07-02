package com.rojas.gestiontareas.gestion_tareas_api.dto;

import lombok.Data;
// No necesitamos @AllArgsConstructor aquí

@Data
public class TokenDTO {
    private String accessToken;
    private String tokenType = "Bearer";

    public TokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}