package com.cdental.pagos_service.service;

import com.cdental.pagos_service.dto.PagoDTO;
import com.cdental.pagos_service.exception.PagoException;
import com.cdental.pagos_service.model.Pago;
import com.cdental.pagos_service.repository.PagoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoService {

    private static final Logger logger = LoggerFactory.getLogger(PagoService.class);
    private final PagoRepository repository;

    public PagoService(PagoRepository repository) {
        this.repository = repository;
    }

    public List<PagoDTO> obtenerTodos() {
        logger.info("Iniciando consulta global de pagos registrados en el sistema");
        return repository.findAll().stream().map(this::convertirADto).collect(Collectors.toList());
    }

    public PagoDTO obtenerPorId(Long id) {
        logger.info("Iniciando consulta de pago por ID");
        Pago pago = repository.findById(id)
                .orElseThrow(() -> new PagoException("Registro de pago no encontrado con el ID: " + id));
        return convertirADto(pago);
    }

    public PagoDTO crear(PagoDTO dto) {
        logger.info("Iniciando el registro y procesamiento de un nuevo pago clínico");
        Pago pago = new Pago();
        pago.setHistorialId(dto.getHistorialId());
        pago.setMonto(dto.getMonto());
        pago.setMetodoPago(dto.getMetodoPago());
        if (dto.getCompletado() != null) {
            pago.setCompletado(dto.getCompletado());
        }

        return convertirADto(repository.save(pago));
    }

    public PagoDTO actualizar(Long id, PagoDTO dto) {
        logger.info("Actualizando pago con ID: {}", id);
        
        Pago pago = repository.findById(id)
                .orElseThrow(() -> new PagoException("No se puede actualizar. Pago no encontrado con ID: " + id));
        
        pago.setHistorialId(dto.getHistorialId());
        pago.setMonto(dto.getMonto());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setFechaPago(dto.getFechaPago());

        if (dto.getCompletado() != null) {
            pago.setCompletado(dto.getCompletado());
        }
        
        return convertirADto(repository.save(pago));
    }

    public void eliminar(Long id) {
        logger.info("Eliminando pago con ID: {}", id);
        Pago pago = repository.findById(id)
                .orElseThrow(() -> new PagoException("No se puede eliminar. Pago no encontrado con ID: " + id));
        repository.delete(pago);
    }

    private PagoDTO convertirADto(Pago entidad) {
        PagoDTO dto = new PagoDTO();
        dto.setId(entidad.getId());
        dto.setHistorialId(entidad.getHistorialId());
        dto.setMonto(entidad.getMonto());
        dto.setMetodoPago(entidad.getMetodoPago());
        dto.setFechaPago(entidad.getFechaPago());
        dto.setCompletado(entidad.getCompletado());
        return dto;
    }
}