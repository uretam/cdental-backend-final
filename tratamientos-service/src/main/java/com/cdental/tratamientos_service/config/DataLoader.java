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
            Tratamiento t3 = new Tratamiento();

            t3.setNombre("Endodoncia Unirradicular");
            t3.setDescripcion("Conducto en pieza de una raíz.");
            t3.setPrecioBase(new BigDecimal("120000.00"));
            t3.setDuracionEstimadaMinutos(60);
            t3.setActivo(true);
            repository.save(t3);
        }
    }
}