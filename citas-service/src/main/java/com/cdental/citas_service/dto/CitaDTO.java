package com.cdental.citas_service.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CitaDTO extends RepresentationModel<CitaDTO> {
    
    private Long id;

    @NotNull(message = "El ID del paciente es obligatorio")
    private Long pacienteId;

    @NotNull(message = "El ID del odontólogo es obligatorio")
    private Long odontologoId;

    @NotNull(message = "La fecha y hora de la cita es obligatoria")
    @FutureOrPresent(message = "La cita no puede ser agendada en una fecha pasada")
    private LocalDateTime fechaHora;

    @NotBlank(message = "El motivo de la cita es obligatorio")
    private String motivo;

    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
}