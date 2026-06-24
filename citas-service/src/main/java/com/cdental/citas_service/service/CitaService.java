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
        logger.info("Obteniendo todas las citas");
        return repository.findAll().stream()
                .map(this::convertirADtoEnriquecido)
                .collect(Collectors.toList());
    }

    public CitaDTO obtenerPorId(Long id) {
        logger.info("Obteniendo cita por id: {}", id);
        Cita cita = repository.findById(id)
                .orElseThrow(() -> new CitaException("Cita no encontrada con ID: " + id));
        return convertirADtoEnriquecido(cita);
    }

    public CitaDTO crear(CitaDTO dto) {
        logger.info("Creando una nueva cita");
        try {
            pacienteClient.obtenerPorId(dto.getPaciente_id());
            odontologoClient.obtenerPorId(dto.getOdontologo_id());
        } catch (Exception e) {
            throw new CitaException("No se pudo crear la cita: El Paciente o el Odontólogo no existen.");
        }

        Cita cita = new Cita();
        cita.setPaciente_id(dto.getPaciente_id());
        cita.setOdontologo_id(dto.getOdontologo_id());
        cita.setFecha_hora(dto.getFecha_hora());
        cita.setMotivo(dto.getMotivo());

        return convertirADtoEnriquecido(repository.save(cita));
    }

    public CitaDTO actualizar(Long id, CitaDTO dto) {
        logger.info("Actualizando cita con id: {}", id);
        Cita cita = repository.findById(id)
                .orElseThrow(() -> new CitaException("No se puede actualizar. Cita no encontrada con ID: " + id));

        try {
            pacienteClient.obtenerPorId(dto.getPaciente_id());
            odontologoClient.obtenerPorId(dto.getOdontologo_id());
        } catch (Exception e) {
            throw new CitaException("No se pudo actualizar la cita: El Paciente o el Odontólogo no existen.");
        }

        cita.setPaciente_id(dto.getPaciente_id());
        cita.setOdontologo_id(dto.getOdontologo_id());
        cita.setFecha_hora(dto.getFecha_hora());
        cita.setMotivo(dto.getMotivo());

        return convertirADtoEnriquecido(repository.save(cita));
    }

    public void eliminar(Long id) {
        logger.info("Eliminando cita con id: {}", id);
        Cita cita = repository.findById(id)
                .orElseThrow(() -> new CitaException("No se puede eliminar. Cita no encontrada con ID: " + id));
        repository.delete(cita);
    }

    private CitaDTO convertirADtoEnriquecido(Cita cita) {
        CitaDTO dto = new CitaDTO();
        dto.setId(cita.getId());
        dto.setPaciente_id(cita.getPaciente_id());
        dto.setOdontologo_id(cita.getOdontologo_id());
        dto.setFecha_hora(cita.getFecha_hora());
        dto.setMotivo(cita.getMotivo());

        try {
            dto.setPaciente(pacienteClient.obtenerPorId(cita.getPaciente_id()));
            dto.setOdontologo(odontologoClient.obtenerPorId(cita.getOdontologo_id()));
        } catch (Exception e) {
            // Se mantiene silencioso si falla la red, igual que tus otros MS
        }
        return dto;
    }
}