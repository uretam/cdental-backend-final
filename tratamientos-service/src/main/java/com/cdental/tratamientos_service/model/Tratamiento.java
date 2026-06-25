package com.cdental.tratamientos_service.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tratamientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion; // Mapeado directo por nombre simple

    @Column(name = "precio_base", nullable = false)
    private BigDecimal precioBase;

    @Column(name = "duracion_estimada_minutos", nullable = false)
    private Integer duracionEstimadaMinutos;

    @Column(nullable = false)
    private Boolean activo = true;
}