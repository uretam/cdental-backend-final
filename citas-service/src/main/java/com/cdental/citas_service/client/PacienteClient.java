package com.cdental.citas_service.client;

import com.cdental.citas_service.dto.PacienteDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "paciente-service", url = "${paciente.service.url}")
public interface PacienteClient {
    @GetMapping("/{id}")
    PacienteDTO obtenerPorId(@PathVariable("id") Long id);
}
