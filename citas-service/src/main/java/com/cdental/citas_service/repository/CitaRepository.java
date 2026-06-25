package com.cdental.citas_service.repository;

import com.cdental.citas_service.model.Cita;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
}