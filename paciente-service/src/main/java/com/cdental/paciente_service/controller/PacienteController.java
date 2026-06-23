package com.cdental.paciente_service.controller;

import com.cdental.paciente_service.dto.PacienteDTO;
import com.cdental.paciente_service.service.PacienteService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<PacienteDTO>>> obtenerTodos() {
        List<EntityModel<PacienteDTO>> pacientes = service.obtenerTodos().stream()
                .map(dto -> EntityModel.of(dto,
                        linkTo(methodOn(PacienteController.class).obtenerPorId(dto.getId())).withSelfRel(),
                        linkTo(methodOn(PacienteController.class).obtenerTodos()).withRel("pacientes")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(pacientes,
                linkTo(methodOn(PacienteController.class).obtenerTodos()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PacienteDTO>> obtenerPorId(@PathVariable Long id) {
        PacienteDTO dto = service.obtenerPorId(id);
        return ResponseEntity.ok(EntityModel.of(dto,
                linkTo(methodOn(PacienteController.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(PacienteController.class).obtenerTodos()).withRel("pacientes")));
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<EntityModel<PacienteDTO>> obtenerPorRut(@PathVariable String rut) {
        PacienteDTO dto = service.obtenerPorRut(rut);
        return ResponseEntity.ok(EntityModel.of(dto,
                linkTo(methodOn(PacienteController.class).obtenerPorRut(rut)).withSelfRel(),
                linkTo(methodOn(PacienteController.class).obtenerTodos()).withRel("pacientes")));
    }
}