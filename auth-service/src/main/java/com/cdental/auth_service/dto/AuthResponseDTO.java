package com.cdental.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // En DTOs de respuesta basta con Getter ya que son de solo lectura (inmutables)
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String username;
    private String role;
}