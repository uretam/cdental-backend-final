package com.cdental.citas_service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OdontologoDTO {
    private Long id;
    private String rut;
    private String nombre;
    private String especialidad;
}