package com.cdental.insumos_service.config;

import com.cdental.insumos_service.model.Insumo;
import com.cdental.insumos_service.repository.InsumoRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final InsumoRepository repository;

    public DataLoader(InsumoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() <= 7) {
            Insumo i1 = new Insumo();
            i1.setNombre("Mascarillas Quirúrgicas Desechables");
            i1.setStockActual(8);
            i1.setStockMinimo(50);
            i1.setActivo(true);
            repository.save(i1);

            Insumo i2 = new Insumo();
            i2.setNombre("Resina Compuesta A2 (10g)");
            i2.setStockActual(25);
            i2.setStockMinimo(10);
            i2.setActivo(true);
            repository.save(i2);

            Insumo i3 = new Insumo();
            i3.setNombre("Agujas Dentales 27G Cortas");
            i3.setStockActual(3);
            i3.setStockMinimo(30);
            i3.setActivo(true);
            repository.save(i3);
        }
    }
}
