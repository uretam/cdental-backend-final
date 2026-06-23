package com.cdental.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    private String username;

    @NotBlank(message = "La contrasena no puede estar vacia")
    private String password;
}