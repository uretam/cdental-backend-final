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
        Odontologo odontologo = repository.findById(id)
                .orElseThrow(() -> new OdontologoException("Odontologo no encontrado con ID: " + id));
        return convertirADto(odontologo);
    }

    public OdontologoDTO crear(OdontologoDTO dto) {
        logger.info("Registrando nuevo odontologo con RUT: {}", dto.getRut());
        
        if (!validarRutChileno(dto.getRut())) {
            throw new OdontologoException("El RUT ingresado no es válido mediante el algoritmo de módulo 11");
        }

        if (repository.findByRut(dto.getRut()).isPresent()) {
            throw new OdontologoException("El odontologo con RUT " + dto.getRut() + " ya existe");
        }
        
        Odontologo odontologo = new Odontologo();
        odontologo.setRut(dto.getRut());
        odontologo.setNombre(dto.getNombre());
        odontologo.setEspecialidad(dto.getEspecialidad());

        return convertirADto(repository.save(odontologo));
    }

    public OdontologoDTO actualizar(Long id, OdontologoDTO dto) {
        logger.info("Modificando datos del odontologo con ID: {}", id);
        
        Odontologo odontologo = repository.findById(id)
                .orElseThrow(() -> new OdontologoException("No se puede modificar. Odontologo no encontrado con ID: " + id));
        
        odontologo.setNombre(dto.getNombre());
        odontologo.setEspecialidad(dto.getEspecialidad());

        return convertirADto(repository.save(odontologo));
    }

    public void eliminar(Long id) {
        logger.info("Dando de baja al odontologo con ID: {}", id);
        Odontologo odontologo = repository.findById(id)
                .orElseThrow(() -> new OdontologoException("No se puede eliminar. Odontologo no encontrado con ID: " + id));
        repository.delete(odontologo);
    }

    private OdontologoDTO convertirADto(Odontologo odontologo) {
        OdontologoDTO dto = new OdontologoDTO();
        dto.setId(odontologo.getId());
        dto.setRut(odontologo.getRut());
        dto.setNombre(odontologo.getNombre());
        dto.setEspecialidad(odontologo.getEspecialidad());
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