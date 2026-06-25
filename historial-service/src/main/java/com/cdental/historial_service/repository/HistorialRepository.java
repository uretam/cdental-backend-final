package com.cdental.historial_service.repository;

import com.cdental.historial_service.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialRepository extends JpaRepository<Historial, Long> {
}