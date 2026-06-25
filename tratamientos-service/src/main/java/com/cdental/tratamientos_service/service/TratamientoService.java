package com.cdental.tratamientos_service.service;

import com.cdental.tratamientos_service.dto.TratamientoDTO;
import com.cdental.tratamientos_service.exception.TratamientoException;
import com.cdental.tratamientos_service.model.Tratamiento;
import com.cdental.tratamientos_service.repository.TratamientoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TratamientoService {

    private static final Logger logger = LoggerFactory.getLogger(TratamientoService.class);
    private final TratamientoRepository repository;

    public TratamientoService(TratamientoRepository repository) {
        this.repository = repository;
    }

    public List<TratamientoDTO> obtenerTodos() {
        logger.info("Buscando lista completa de tratamientos");
        return repository.findAll().stream().map(this::convertirADto).collect(Collectors.toList());
    }

    public TratamientoDTO obtenerPorId(Long id) {
        logger.info("Buscando tratamiento con ID: {}", id);
        Tratamiento tratamiento = repository.findById(id)
                .orElseThrow(() -> new TratamientoException("Tratamiento no encontrado con ID: " + id));
        return convertirADto(tratamiento);
    }

    public TratamientoDTO crear(TratamientoDTO dto) {
        logger.info("Registrando nuevo tratamiento con nombre: {}", dto.getNombre());
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setNombre(dto.getNombre());
        tratamiento.setDescripcion(dto.getDescripcion());
        tratamiento.setPrecioBase(dto.getPrecioBase());
        tratamiento.setDuracionEstimadaMinutos(dto.getDuracionEstimadaMinutos());
        tratamiento.setActivo(dto.getActivo() == null || dto.getActivo());
        
        return convertirADto(repository.save(tratamiento));
    }

    public TratamientoDTO actualizar(Long id, TratamientoDTO dto) {
        logger.info("Actualizando tratamiento con ID: {}", id);
        Tratamiento tratamiento = repository.findById(id)
                .orElseThrow(() -> new TratamientoException("No se puede actualizar. Tratamiento no encontrado con ID: " + id));

        tratamiento.setNombre(dto.getNombre());
        tratamiento.setDescripcion(dto.getDescripcion());
        tratamiento.setPrecioBase(dto.getPrecioBase());
        tratamiento.setDuracionEstimadaMinutos(dto.getDuracionEstimadaMinutos());
        tratamiento.setActivo(dto.getActivo() == null ? tratamiento.getActivo() : dto.getActivo());

        return convertirADto(repository.save(tratamiento));
    }

    public void eliminar(Long id) {
        logger.info("Eliminando tratamiento con ID: {}", id);
        Tratamiento tratamiento = repository.findById(id)
                .orElseThrow(() -> new TratamientoException("No se puede eliminar. Tratamiento no encontrado con ID: " + id));
        repository.delete(tratamiento);
    }

    private TratamientoDTO convertirADto(Tratamiento entidad) {
        return new TratamientoDTO(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getDescripcion(),
                entidad.getPrecioBase(),
                entidad.getDuracionEstimadaMinutos(),
                entidad.getActivo()
        );
    }
}