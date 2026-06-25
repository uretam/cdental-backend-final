package com.cdental.pagos_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "historial_id", nullable = false)
    private Long historialId;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double monto;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    @Column(name = "fecha_pago", nullable = false, updatable = false)
    private LocalDateTime fechaPago = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean completado = true;
}
