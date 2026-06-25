package com.cdental.tratamientos_service.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String descripcion;

    @Column(name = "precio_base", nullable = false)
    private BigDecimal precioBase;

    @Column(name = "duracion_estimada_minutos", nullable = false)
    private Integer duracionEstimadaMinutos;

    @Column(nullable = false)
    private Boolean activo = true;
}