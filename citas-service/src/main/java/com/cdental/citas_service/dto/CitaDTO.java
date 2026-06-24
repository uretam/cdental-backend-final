package com.cdental.citas_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CitaDTO {
    private Long id;
    private Long paciente_id;
    private Long odontologo_id;
    private LocalDateTime fecha_hora;
    private String motivo;

    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
}