package com.cdental.historial_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class HistorialDTO extends RepresentationModel<HistorialDTO> {
    private Long id;

    @NotNull(message = "El ID del paciente es obligatorio")
    private Long pacienteId;

    @NotNull(message = "El ID de la cita es obligatorio")
    private Long citaId;

    @NotNull(message = "El ID del tratamiento es obligatorio")
    private Long tratamientoId;

    @Size(max = 255, message = "Las observaciones no pueden superar los 255 caracteres")
    private String observaciones;

    private LocalDateTime fechaRegistro;
    private Boolean activo;
}