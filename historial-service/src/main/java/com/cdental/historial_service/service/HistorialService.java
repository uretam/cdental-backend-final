package com.cdental.historial_service.service;

import com.cdental.historial_service.dto.HistorialDTO;
import com.cdental.historial_service.exception.HistorialException;
import com.cdental.historial_service.model.Historial;
import com.cdental.historial_service.repository.HistorialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistorialService {

    private static final Logger logger = LoggerFactory.getLogger(HistorialService.class);
    private final HistorialRepository repository;

    public HistorialService(HistorialRepository repository) {
        this.repository = repository;
    }

    public List<HistorialDTO> obtenerTodos() {
        logger.info("Iniciando consulta global de historiales clínicos");
        return repository.findAll().stream().map(this::convertirADto).collect(Collectors.toList());
    }

    public HistorialDTO obtenerPorId(Long id) {
        logger.info("Iniciando consulta de historial clínico por ID");
        Historial historial = repository.findById(id)
                .orElseThrow(() -> new HistorialException("Historial clínico no encontrado con el ID: " + id));
        return convertirADto(historial);
    }

    public HistorialDTO crear(HistorialDTO dto) {
        logger.info("Iniciando creación de un registro en el historial clínico");
        Historial historial = convertirAEntidad(dto);
        return convertirADto(repository.save(historial));
    }

    public HistorialDTO actualizar(Long id, HistorialDTO dto) {
        logger.info("Modificando historial clínico con ID: {}", id);

        Historial historial = repository.findById(id)
                .orElseThrow(() -> new HistorialException("No se puede modificar. Historial clínico no encontrado con ID: " + id));

        historial.setPacienteId(dto.getPacienteId());
        historial.setCitaId(dto.getCitaId());
        historial.setTratamientoId(dto.getTratamientoId());
        historial.setObservaciones(dto.getObservaciones());
        
        if (dto.getFechaRegistro() != null) {
            historial.setFechaRegistro(dto.getFechaRegistro());
        }
        if (dto.getActivo() != null) {
            historial.setActivo(dto.getActivo());
        }

        return convertirADto(repository.save(historial));
    }

    public void eliminar(Long id) {
        logger.info("Dando de baja al historial clínico con ID: {}", id);
        Historial historial = repository.findById(id)
                .orElseThrow(() -> new HistorialException("No se puede eliminar. Historial clínico no encontrado con ID: " + id));
        repository.delete(historial);
    }

    private HistorialDTO convertirADto(Historial entidad) {
        return new HistorialDTO(
                entidad.getId(),
                entidad.getPacienteId(),
                entidad.getCitaId(),
                entidad.getTratamientoId(),
                entidad.getObservaciones(),
                entidad.getFechaRegistro(),
                entidad.getActivo()
        );
    }

    private Historial convertirAEntidad(HistorialDTO dto) {
        Historial h = new Historial();
        h.setPacienteId(dto.getPacienteId());
        h.setCitaId(dto.getCitaId());
        h.setTratamientoId(dto.getTratamientoId());
        h.setObservaciones(dto.getObservaciones());
        if (dto.getActivo() != null) {
            h.setActivo(dto.getActivo());
        }
        return h;
    }
}