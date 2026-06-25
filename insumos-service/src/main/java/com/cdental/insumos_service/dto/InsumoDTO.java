package com.cdental.insumos_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class InsumoDTO extends RepresentationModel<InsumoDTO> {
    private Long id;

    @NotBlank(message = "El nombre del insumo es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotNull(message = "El stock actual no puede ser nulo")
    @Min(value = 0, message = "El stock actual no puede ser un valor negativo")
    private Integer stockActual;

    @NotNull(message = "El stock mínimo no puede ser nulo")
    @Min(value = 0, message = "El stock mínimo no puede ser un valor negativo")
    private Integer stockMinimo;

    private Boolean activo;

    private Boolean necesitaReposicion;
}
