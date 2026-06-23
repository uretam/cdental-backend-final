package com.cdental.paciente_service.dto;

import org.springframework.hateoas.RepresentationModel;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class PacienteDTO extends RepresentationModel<PacienteDTO> {
    private Long id;
    private String rut;
    private String nombre;
    private String correo;
}