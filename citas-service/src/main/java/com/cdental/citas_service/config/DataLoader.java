package com.cdental.citas_service.config;

import com.cdental.citas_service.model.Cita;
import com.cdental.citas_service.repository.CitaRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {
    private final CitaRepository citaRepository;

    public DataLoader(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (citaRepository.count() <= 1) { 
            Cita cita = new Cita();
            cita.setPacienteId(2L); // Relacionado al paciente con ID 2
            cita.setOdontologoId(1L); // Relacionado al odontólogo con ID 1
            cita.setFechaHora(LocalDateTime.of(2026, 11, 20, 15, 30));
            cita.setMotivo("Tratamiento de conducto (Endodoncia) - Sesión 1");

            citaRepository.save(cita);
        }
    }
}