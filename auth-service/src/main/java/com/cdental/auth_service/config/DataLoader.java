package com.cdental.auth_service.config;

import com.cdental.auth_service.model.Usuario;
import com.cdental.auth_service.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UsuarioRepository repository;

    public DataLoader(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Si ya estan los 7 de Liquibase, agregamos los 3 restantes
        if (repository.count() <= 7) {
            Usuario u1 = new Usuario();
            u1.setUsername("paciente_user1");
            u1.setPassword("pass123");
            u1.setRole("PACIENTE");
            repository.save(u1);

            Usuario u2 = new Usuario();
            u2.setUsername("paciente_user2");
            u2.setPassword("pass123");
            u2.setRole("PACIENTE");
            repository.save(u2);
            
            Usuario u3 = new Usuario();
            u3.setUsername("paciente_user3");
            u3.setPassword("pass123");
            u3.setRole("PACIENTE");
            repository.save(u3);
        }
    }
}