package com.cdental.tratamientos_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TratamientoDTO extends RepresentationModel<TratamientoDTO> {
    private Long id;

    @NotBlank(message = "El nombre del tratamiento no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String descripcion;

    @NotNull(message = "El precio base es obligatorio")
    @Positive(message = "El precio base debe ser mayor a cero")
    private BigDecimal precioBase;

    @NotNull(message = "La duración estimada es obligatoria")
    @Min(value = 5, message = "La duración mínima debe ser de 5 minutos")
    private Integer duracionEstimadaMinutos;

    private Boolean activo;
}