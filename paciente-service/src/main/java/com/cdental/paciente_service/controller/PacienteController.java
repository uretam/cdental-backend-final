package com.cdental.paciente_service.controller;

import com.cdental.paciente_service.dto.PacienteDTO;
import com.cdental.paciente_service.service.PacienteService;

import jakarta.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<PacienteDTO> obtenerPorRut(@PathVariable String rut) {
        return ResponseEntity.ok(service.obtenerPorRut(rut));
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> crear(@Valid @RequestBody PacienteDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PacienteDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}