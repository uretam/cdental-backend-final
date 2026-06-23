package com.cdental.odontologo_service.service;

import com.cdental.odontologo_service.dto.OdontologoDTO;
import com.cdental.odontologo_service.exception.OdontologoException;
import com.cdental.odontologo_service.model.Odontologo;
import com.cdental.odontologo_service.repository.OdontologoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OdontologoService {
    private static final Logger logger = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository repository;

    public OdontologoService(OdontologoRepository repository) {
        this.repository = repository;
    }

    public List<OdontologoDTO> obtenerTodos() {
        logger.info("Buscando listado global de odontologos");
        return repository.findAll().stream().map(this::convertirADto).collect(Collectors.toList());
    }

    public OdontologoDTO obtenerPorId(Long id) {
        logger.info("Buscando odontologo con ID: {}", id);
        Odontologo o = repository.findById(id)
                .orElseThrow(() -> new OdontologoException("Odontologo no encontrado con ID: " + id));
        return convertirADto(o);
    }

    public OdontologoDTO crear(OdontologoDTO dto) {
        logger.info("Registrando nuevo odontologo con RUT: {}", dto.getRut());
        if (repository.findByRut(dto.getRut()).isPresent()) {
            throw new OdontologoException("El odontologo con RUT " + dto.getRut() + " ya existe");
        }
        Odontologo o = new Odontologo();
        o.setRut(dto.getRut());
        o.setNombre(dto.getNombre());
        o.setEspecialidad(dto.getEspecialidad());

        Odontologo guardado = repository.save(o);
        return convertirADto(guardado);
    }

    public OdontologoDTO actualizar(Long id, OdontologoDTO dto) {
        logger.info("Modificando datos del odontologo con ID: {}", id);
        Odontologo o = repository.findById(id)
                .orElseThrow(() -> new OdontologoException("No se puede modificar. Odontologo no encontrado con ID: " + id));
        
        o.setNombre(dto.getNombre());
        o.setEspecialidad(dto.getEspecialidad());

        Odontologo actualizado = repository.save(o);
        return convertirADto(actualizado);
    }

    public void eliminar(Long id) {
        logger.info("Dando de baja al odontologo con ID: {}", id);
        Odontologo o = repository.findById(id)
                .orElseThrow(() -> new OdontologoException("No se puede eliminar. Odontologo no encontrado con ID: " + id));
        repository.delete(o);
    }

    private OdontologoDTO convertirADto(Odontologo o) {
        OdontologoDTO dto = new OdontologoDTO();
        dto.setId(o.getId());
        dto.setRut(o.getRut());
        dto.setNombre(o.getNombre());
        dto.setEspecialidad(o.getEspecialidad());
        return dto;
    }
}