package com.cdental.pagos_service.repository;

import com.cdental.pagos_service.model.Pago;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}