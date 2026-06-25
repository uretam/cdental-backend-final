package com.cdental.insumos_service.repository;

import com.cdental.insumos_service.model.Insumo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InsumoRepository extends JpaRepository<Insumo, Long> {
}