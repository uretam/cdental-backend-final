package com.cdental.tratamientos_service.config;

import com.cdental.tratamientos_service.model.Tratamiento;
import com.cdental.tratamientos_service.repository.TratamientoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final TratamientoRepository repository;

    public DataLoader(TratamientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() <= 2) {
            Tratamiento t3 = new Tratamiento(null, "Endodoncia Unirradicular", "Conducto en pieza de una raíz.", new BigDecimal("120000.00"), 60, true);
            repository.save(t3);
        }
    }
}