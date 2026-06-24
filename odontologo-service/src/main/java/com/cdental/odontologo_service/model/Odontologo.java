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
    private String especialidad;
}