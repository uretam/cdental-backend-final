package com.cdental.pagos_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.springframework.hateoas.RepresentationModel;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO extends RepresentationModel<PagoDTO> {
    private Long id;

    @NotNull(message = "El ID del historial clínico es obligatorio")
    private Long historialId;

    @NotNull(message = "El monto del pago es obligatorio")
    @Positive(message = "El monto debe ser un valor positivo")
    private Double monto;

    @NotBlank(message = "El método de pago es obligatorio (EFECTIVO, TARJETA, TRANSFERENCIA)")
    private String metodoPago;

    private LocalDateTime fechaPago;
    private Boolean completado;
}