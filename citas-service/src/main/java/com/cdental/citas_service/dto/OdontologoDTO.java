package com.cdental.citas_service.dto;

import lombok.Data;

@Data
public class OdontologoDTO {
    private Long id;
    private String rut;
    private String nombre;
    private String especialidad;
}