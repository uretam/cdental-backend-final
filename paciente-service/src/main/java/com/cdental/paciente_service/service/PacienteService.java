package com.cdental.paciente_service.service;

import com.cdental.paciente_service.dto.PacienteDTO;
import com.cdental.paciente_service.exception.PacienteException;
import com.cdental.paciente_service.model.Paciente;
import com.cdental.paciente_service.repository.PacienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {
    private static final Logger logger = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public List<PacienteDTO> obtenerTodos() {
        logger.info("Buscando lista completa de pacientes");
        return repository.findAll().stream().map(this::convertirADto).collect(Collectors.toList());
    }

    public PacienteDTO obtenerPorId(Long id) {
        logger.info("Buscando paciente con ID: {}", id);
        Paciente p = repository.findById(id)
                .orElseThrow(() -> new PacienteException("Paciente no encontrado con ID: " + id));
        return convertirADto(p);
    }

    public PacienteDTO obtenerPorRut(String rut) {
        logger.info("Buscando paciente con RUT: {}", rut);
        Paciente p = repository.findByRut(rut)
                .orElseThrow(() -> new PacienteException("Paciente no encontrado con RUT: " + rut));
        return convertirADto(p);
    }

    private PacienteDTO convertirADto(Paciente p) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(p.getId());
        dto.setRut(p.getRut());
        dto.setNombre(p.getNombre());
        dto.setCorreo(p.getCorreo());
        return dto;
    }
}