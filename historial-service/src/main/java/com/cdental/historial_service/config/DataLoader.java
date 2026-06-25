package com.cdental.historial_service.config;

import com.cdental.historial_service.model.Historial;
import com.cdental.historial_service.repository.HistorialRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final HistorialRepository repository;

    public DataLoader(HistorialRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() <= 7) {
            Historial h1 = new Historial();
            h1.setPacienteId(1L);
            h1.setCitaId(801L);
            h1.setTratamientoId(2L);
            h1.setObservaciones("Segunda sesion de blanqueamiento. Resultado satisfactorio.");
            h1.setFechaRegistro(LocalDateTime.now().minusDays(10));
            h1.setActivo(true);
            repository.save(h1);

            Historial h2 = new Historial();
            h2.setPacienteId(2L);
            h2.setCitaId(901L);
            h2.setTratamientoId(1L);
            h2.setObservaciones("Limpieza semestral. Higiene bucal en buen estado.");
            h2.setFechaRegistro(LocalDateTime.now().minusDays(5));
            h2.setActivo(true);
            repository.save(h2);

            Historial h3 = new Historial();
            h3.setPacienteId(3L);
            h3.setCitaId(1001L);
            h3.setTratamientoId(4L);
            h3.setObservaciones("Segunda sesion endodoncia. Obturacion definitiva realizada.");
            h3.setFechaRegistro(LocalDateTime.now().minusDays(2));
            h3.setActivo(true);
            repository.save(h3);
        }
    }
}
