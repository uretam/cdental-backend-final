package com.cdental.odontologo_service.repository;

import com.cdental.odontologo_service.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    Optional<Odontologo> findByRut(String rut);
}