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
    
    @Column(name = "especialidad") // ¡Aquí debe ir para que Hibernate lo valide en el campo!
    private String specialty; 

    // Mantenemos estos métodos manuales para que la capa Service no falle al compilar
    public String getEspecialidad() { 
        return this.specialty; 
    }
    
    public void setEspecialidad(String especialidad) { 
        this.specialty = especialidad; 
    }
}