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

    public PacienteDTO crear(PacienteDTO dto) {
        logger.info("Registrando nuevo paciente con RUT: {}", dto.getRut());
        
        if (!validarRutChileno(dto.getRut())) {
            throw new PacienteException("El RUT ingresado no es válido mediante el algoritmo de módulo 11");
        }
        
        if (repository.findByRut(dto.getRut()).isPresent()) {
            throw new PacienteException("El RUT " + dto.getRut() + " ya se encuentra registrado");
        }
        
        Paciente p = new Paciente();
        p.setRut(dto.getRut());
        p.setNombre(dto.getNombre());
        p.setCorreo(dto.getCorreo());
        
        return convertirADto(repository.save(p));
    }

    public PacienteDTO actualizar(Long id, PacienteDTO dto) {
        logger.info("Actualizando datos del paciente con ID: {}", id);
        
        Paciente p = repository.findById(id)
                .orElseThrow(() -> new PacienteException("No se puede actualizar. Paciente no encontrado con ID: " + id));
        
        p.setNombre(dto.getNombre());
        p.setCorreo(dto.getCorreo());
        
        return convertirADto(repository.save(p));
    }

    public void eliminar(Long id) {
        logger.info("Eliminando paciente con ID: {}", id);
        Paciente p = repository.findById(id)
                .orElseThrow(() -> new PacienteException("No se puede eliminar. Paciente no encontrado con ID: " + id));
        repository.delete(p);
    }

    private PacienteDTO convertirADto(Paciente p) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(p.getId());
        dto.setRut(p.getRut());
        dto.setNombre(p.getNombre());
        dto.setCorreo(p.getCorreo());
        return dto;
    }

    private boolean validarRutChileno(String rut) {
        if (rut == null) return false;
        String limpio = rut.replace(".", "").replace("-", "").toUpperCase();
        if (limpio.length() < 2) return false;
        
        String cuerpo = limpio.substring(0, limpio.length() - 1);
        char dvInput = limpio.charAt(limpio.length() - 1);
        
        try {
            int rutNum = Integer.parseInt(cuerpo);
            int m = 0, s = 1;
            for (; rutNum != 0; rutNum /= 10) {
                s = (s + rutNum % 10 * (9 - m++ % 6)) % 11;
            }
            char dvEsperado = (char) (s != 0 ? s + 47 : 75);
            return dvInput == dvEsperado;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}