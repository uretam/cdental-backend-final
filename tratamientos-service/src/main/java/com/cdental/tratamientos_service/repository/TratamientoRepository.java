package com.cdental.tratamientos_service.repository;

import com.cdental.tratamientos_service.model.Tratamiento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
}