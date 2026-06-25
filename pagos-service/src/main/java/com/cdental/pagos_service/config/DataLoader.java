package com.cdental.pagos_service.config;

import com.cdental.pagos_service.model.Pago;
import com.cdental.pagos_service.repository.PagoRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final PagoRepository repository;

    public DataLoader(PagoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() <= 7) {
            Pago p1 = new Pago();
            p1.setHistorialId(2L);
            p1.setMonto(new BigDecimal("55000.00"));
            p1.setMetodoPago("TARJETA");
            p1.setFechaPago(LocalDateTime.now().minusDays(5));
            p1.setCompletado(true);
            repository.save(p1);

            Pago p2 = new Pago();
            p2.setHistorialId(3L);
            p2.setMonto(new BigDecimal("120000.00"));
            p2.setMetodoPago("TRANSFERENCIA");
            p2.setFechaPago(LocalDateTime.now().minusDays(3));
            p2.setCompletado(true);
            repository.save(p2);

            Pago p3 = new Pago();
            p3.setHistorialId(4L);
            p3.setMonto(new BigDecimal("85000.00"));
            p3.setMetodoPago("EFECTIVO");
            p3.setFechaPago(LocalDateTime.now().minusDays(1));
            p3.setCompletado(false);
            repository.save(p3);
        }
    }
}
