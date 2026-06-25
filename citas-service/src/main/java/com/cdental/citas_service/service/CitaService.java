package com.cdental.citas_service.service;

import com.cdental.citas_service.client.OdontologoClient;
import com.cdental.citas_service.client.PacienteClient;
import com.cdental.citas_service.dto.CitaDTO;
import com.cdental.citas_service.exception.CitaException;
import com.cdental.citas_service.model.Cita;
import com.cdental.citas_service.repository.CitaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService {
    
    private static final Logger logger = LoggerFactory.getLogger(CitaService.class);
    
    private final CitaRepository repository;
    private final PacienteClient pacienteClient;
    private final OdontologoClient odontologoClient;

    public CitaService(CitaRepository repository, PacienteClient pacienteClient, OdontologoClient odontologoClient) {
        this.repository = repository;
        this.pacienteClient = pacienteClient;
        this.odontologoClient = odontologoClient;
    }

    public List<CitaDTO> obtenerTodas() {
        logger.info("Recuperando el listado historico de citas medicas");
        return repository.findAll().stream()
                .map(this::convertirADtoEnriquecido)
                .collect(Collectors.toList());
    }

    public CitaDTO obtenerPorId(Long id) {
        logger.info("Buscando cita medica con ID: {}", id);
        Cita cita = repository.findById(id)
                .orElseThrow(() -> new CitaException("Cita no encontrada con ID: " + id));
        return convertirADtoEnriquecido(cita);
    }

    public CitaDTO crear(CitaDTO dto) {
        logger.info("Intentando registrar nueva cita para Paciente ID: {} y Odontologo ID: {}", dto.getPacienteId(), dto.getOdontologoId());
        
        validarExistenciaRemota(dto.getPacienteId(), dto.getOdontologoId());

        Cita cita = new Cita();
        cita.setPacienteId(dto.getPacienteId());
        cita.setOdontologoId(dto.getOdontologoId());
        cita.setFechaHora(dto.getFechaHora());
        cita.setMotivo(dto.getMotivo());

        return convertirADtoEnriquecido(repository.save(cita));
    }

    public CitaDTO actualizar(Long id, CitaDTO dto) {
        logger.info("Modificando parametros de la cita con ID: {}", id);
        
        Cita cita = repository.findById(id)
                .orElseThrow(() -> new CitaException("No se puede actualizar. Cita no encontrada con ID: " + id));

        validarExistenciaRemota(dto.getPacienteId(), dto.getOdontologoId());

        cita.setPacienteId(dto.getPacienteId());
        cita.setOdontologoId(dto.getOdontologoId());
        cita.setFechaHora(dto.getFechaHora());
        cita.setMotivo(dto.getMotivo());

        return convertirADtoEnriquecido(repository.save(cita));
    }

    public void eliminar(Long id) {
        logger.info("Removiendo cita medica con ID: {}", id);
        Cita cita = repository.findById(id)
                .orElseThrow(() -> new CitaException("No se puede eliminar. Cita no encontrada con ID: " + id));
        repository.delete(cita);
    }

    private void validarExistenciaRemota(Long pacienteId, Long odontologoId) {
        try {
            pacienteClient.obtenerPorId(pacienteId);
            odontologoClient.obtenerPorId(odontologoId);
        } catch (Exception e) {
            throw new CitaException("Validacion remota fallida: El Paciente o el Odontologo no existen en sus respectivos microservicios.");
        }
    }

    private CitaDTO convertirADtoEnriquecido(Cita cita) {
        CitaDTO dto = new CitaDTO();
        dto.setId(cita.getId());
        dto.setPacienteId(cita.getPacienteId());
        dto.setOdontologoId(cita.getOdontologoId());
        dto.setFechaHora(cita.getFechaHora());
        dto.setMotivo(cita.getMotivo());

        try {
            dto.setPaciente(pacienteClient.obtenerPorId(cita.getPacienteId()));
        } catch (Exception e) {
            logger.warn("No se pudo enriquecer el Paciente con ID: {} (Microservicio caido o inexistente)", cita.getPacienteId());
        }

        try {
            dto.setOdontologo(odontologoClient.obtenerPorId(cita.getOdontologoId()));
        } catch (Exception e) {
            logger.warn("No se pudo enriquecer el Odontologo con ID: {} (Microservicio caido o inexistente)", cita.getOdontologoId());
        }

        return dto;
    }
}