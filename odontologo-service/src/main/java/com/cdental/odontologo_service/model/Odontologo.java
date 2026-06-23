package com.cdental.odontologo_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "odontologos")
@Data
@NoArgsConstructor
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rut;
    private String nombre;
    private String specialty; // Mapeado como especialidad en la base de datos por consistencia con la rúbrica

    @Column(name = "especialidad")
    public String getEspecialidad() { return specialty; }
    public void setEspecialidad(String especialidad) { this.specialty = especialidad; }
}