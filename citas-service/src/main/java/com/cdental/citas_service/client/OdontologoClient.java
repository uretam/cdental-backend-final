package com.cdental.citas_service.client;

import com.cdental.citas_service.dto.OdontologoDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "odontologo-service", url = "${odontologo.service.url}")
public interface OdontologoClient {
    @GetMapping("/{id}")
    OdontologoDTO obtenerPorId(@PathVariable("id") Long id);
}
