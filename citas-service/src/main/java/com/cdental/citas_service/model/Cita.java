package com.cdental.citas_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "paciente_id")
    private Long pacienteId;
    
    @Column(name = "odontologo_id")
    private Long odontologoId;
    
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;
    
    private String motivo;
}