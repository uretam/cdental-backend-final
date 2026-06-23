package com.cdental.auth_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data // Genera automaticamente Getters, Setters, toString, equals y hashCode
@NoArgsConstructor // Genera el constructor vacio obligatorio para JPA
@AllArgsConstructor // Genera el constructor con todos los atributos
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String password;
    private String role;
}