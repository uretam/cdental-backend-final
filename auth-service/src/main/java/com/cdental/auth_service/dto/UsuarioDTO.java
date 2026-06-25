package com.cdental.auth_service.dto;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false) // Evita conflictos con los enlaces de RepresentationModel de HATEOAS
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {
    @NotNull(message = "El id no puede ser nulo")
    private Long id; 

    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    @Size(min = 4, max = 20, message = "El username debe tener entre 4 y 20 caracteres")
    private String username;

    @NotBlank(message = "El rol no puede estar vacio")
    private String role;
}