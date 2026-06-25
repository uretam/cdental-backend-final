package com.cdental.citas_service.controller;

import com.cdental.citas_service.dto.CitaDTO;
import com.cdental.citas_service.service.CitaService;

import jakarta.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/citas")
public class CitaController {

    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<CitaDTO>>> obtenerTodas() {
        List<EntityModel<CitaDTO>> citas = service.obtenerTodas().stream()
                .map(dto -> EntityModel.of(dto,
                        linkTo(methodOn(CitaController.class).obtenerPorId(dto.getId())).withSelfRel(),
                        linkTo(methodOn(CitaController.class).obtenerTodas()).withRel("citas")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(citas,
                linkTo(methodOn(CitaController.class).obtenerTodas()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CitaDTO>> obtenerPorId(@PathVariable Long id) {
        CitaDTO dto = service.obtenerPorId(id);
        return ResponseEntity.ok(EntityModel.of(dto,
                linkTo(methodOn(CitaController.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(CitaController.class).obtenerTodas()).withRel("citas")));
    }

    @PostMapping
    public ResponseEntity<CitaDTO> crear(@Valid @RequestBody CitaDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CitaDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}