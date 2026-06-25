package com.cdental.historial_service.model;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "historiales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @Column(name = "cita_id", nullable = false, unique = true)
    private Long citaId;

    @Column(name = "tratamiento_id", nullable = false)
    private Long tratamientoId;

    private String observaciones;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean activo = true;
}