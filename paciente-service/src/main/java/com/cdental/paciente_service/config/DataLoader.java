package com.cdental.paciente_service.config;

import com.cdental.paciente_service.model.Paciente;
import com.cdental.paciente_service.repository.PacienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final PacienteRepository repository;

    public DataLoader(PacienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() <= 7) {
            Paciente p1 = new Paciente(); 
            p1.setRut("88.888.888-8"); 
            p1.setNombre("Diego Diaz"); 
            p1.setCorreo("diego@mail.com");
            repository.save(p1);

            Paciente p2 = new Paciente(); 
            p2.setRut("99.999.999-9"); 
            p2.setNombre("Elena Silva"); 
            p2.setCorreo("elena@mail.com");
            repository.save(p2);

            Paciente p3 = new Paciente(); 
            p3.setRut("10.000.000-0"); 
            p3.setNombre("Raul Muñoz"); 
            p3.setCorreo("raul@mail.com");
            repository.save(p3);
        }
    }
}