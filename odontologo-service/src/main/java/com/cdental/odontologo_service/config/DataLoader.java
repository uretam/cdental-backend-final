package com.cdental.odontologo_service.config;

import com.cdental.odontologo_service.model.Odontologo;
import com.cdental.odontologo_service.repository.OdontologoRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OdontologoRepository repository;

    public DataLoader(OdontologoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() <= 7) {
            Odontologo o1 = new Odontologo();
            o1.setRut("19.999.999-9");
            o1.setNombre("Dra. Helen Toledo");
            o1.setEspecialidad("Ortodoncia");
            repository.save(o1);

            Odontologo o2 = new Odontologo();
            o2.setRut("20.111.111-1");
            o2.setNombre("Dr. Ian Bravo");
            o2.setEspecialidad("Endodoncia");
            repository.save(o2);

            Odontologo o3 = new Odontologo();
            o3.setRut("21.222.222-2");
            o3.setNombre("Dra. Julia Muñoz");
            o3.setEspecialidad("General");
            repository.save(o3);
        }
    }
}