package com.cdental.insumos_service.service;

import com.cdental.insumos_service.dto.InsumoDTO;
import com.cdental.insumos_service.exception.InsumoException;
import com.cdental.insumos_service.model.Insumo;
import com.cdental.insumos_service.repository.InsumoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsumoService {

    private static final Logger logger = LoggerFactory.getLogger(InsumoService.class);
    private final InsumoRepository repository;

    public InsumoService(InsumoRepository repository) {
        this.repository = repository;
    }

    public List<InsumoDTO> obtenerTodos() {
        logger.info("Iniciando consulta global del inventario de insumos clínicos");
        return repository.findAll().stream().map(this::convertirADto).collect(Collectors.toList());
    }

    public InsumoDTO obtenerPorId(Long id) {
        logger.info("Iniciando consulta de insumo por ID en el inventario");
        Insumo insumo = repository.findById(id)
                .orElseThrow(() -> new InsumoException("Material o insumo clínico no encontrado con el ID: " + id));
        return convertirADto(insumo);
    }

    public List<InsumoDTO> obtenerBajoStock() {
        logger.info("Consultando insumos con stock por debajo del nivel mínimo");
        return repository.findAll().stream()
                .filter(insumo -> insumo.getStockActual() < insumo.getStockMinimo())
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public InsumoDTO crear(InsumoDTO dto) {
        logger.info("Iniciando el registro de un nuevo insumo médico en el stock");

        Insumo insumo = new Insumo();
        insumo.setNombre(dto.getNombre());
        insumo.setStockActual(dto.getStockActual());
        insumo.setStockMinimo(dto.getStockMinimo());
        insumo.setActivo(dto.getActivo() != null ? dto.getActivo() : true);

        InsumoDTO resultado = convertirADto(repository.save(insumo));

        if (insumo.getStockActual() < insumo.getStockMinimo()) {
            logger.warn("ALERTA DE STOCK: El insumo '{}' fue creado con stock ({}) por debajo del mínimo ({}).",
                    insumo.getNombre(), insumo.getStockActual(), insumo.getStockMinimo());
        }

        return resultado;
    }

    public InsumoDTO actualizar(Long id, InsumoDTO dto) {
        logger.info("Modificando datos del insumo con ID: {}", id);

        Insumo insumo = repository.findById(id)
                .orElseThrow(() -> new InsumoException("No se puede modificar. Material o insumo clínico no encontrado con ID: " + id));

        insumo.setNombre(dto.getNombre());
        insumo.setStockActual(dto.getStockActual());
        insumo.setStockMinimo(dto.getStockMinimo());
        insumo.setActivo(dto.getActivo());

        InsumoDTO resultado = convertirADto(repository.save(insumo));

        if (insumo.getStockActual() < insumo.getStockMinimo()) {
            logger.warn("ALERTA DE STOCK: El insumo '{}' (ID: {}) tiene stock ({}) por debajo del mínimo ({}).",
                    insumo.getNombre(), id, insumo.getStockActual(), insumo.getStockMinimo());
        }

        return resultado;
    }

    public void eliminar(Long id) {
        logger.info("Dando de baja al insumo con ID: {}", id);
        Insumo insumo = repository.findById(id)
                .orElseThrow(() -> new InsumoException("No se puede eliminar. Material o insumo clínico no encontrado con ID: " + id));
        repository.delete(insumo);
    }

    private InsumoDTO convertirADto(Insumo i) {
        InsumoDTO dto = new InsumoDTO();
        dto.setId(i.getId());
        dto.setNombre(i.getNombre());
        dto.setStockActual(i.getStockActual());
        dto.setStockMinimo(i.getStockMinimo());
        dto.setActivo(i.getActivo());
        dto.setNecesitaReposicion(i.getStockActual() < i.getStockMinimo());
        return dto;
    }
}
