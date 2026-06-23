package com.cdental.auth_service.dto;

import org.springframework.hateoas.RepresentationModel;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false) // Evita conflictos con los enlaces de RepresentationModel de HATEOAS
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {
    private Long id;
    private String username;
    private String role;
}