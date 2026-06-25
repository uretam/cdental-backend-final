package com.cdental.insumos_service.controller;

import com.cdental.insumos_service.dto.InsumoDTO;
import com.cdental.insumos_service.service.InsumoService;

import jakarta.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/insumos")
public class InsumoController {

    private final InsumoService service;

    public InsumoController(InsumoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<InsumoDTO>>> obtenerTodos() {
        List<EntityModel<InsumoDTO>> insumos = service.obtenerTodos().stream()
                .map(dto -> EntityModel.of(dto,
                        linkTo(methodOn(InsumoController.class).obtenerPorId(dto.getId())).withSelfRel(),
                        linkTo(methodOn(InsumoController.class).obtenerTodos()).withRel("insumos")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(insumos,
                linkTo(methodOn(InsumoController.class).obtenerTodos()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<InsumoDTO>> obtenerPorId(@PathVariable Long id) {
        InsumoDTO dto = service.obtenerPorId(id);
        return ResponseEntity.ok(EntityModel.of(dto,
                linkTo(methodOn(InsumoController.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(InsumoController.class).obtenerTodos()).withRel("insumos")));
    }

    @PostMapping
    public ResponseEntity<InsumoDTO> crear(@Valid @RequestBody InsumoDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsumoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody InsumoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}