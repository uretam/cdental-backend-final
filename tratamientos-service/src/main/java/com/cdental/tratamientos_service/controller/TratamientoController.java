package com.cdental.tratamientos_service.controller;

import com.cdental.tratamientos_service.dto.TratamientoDTO;
import com.cdental.tratamientos_service.service.TratamientoService;

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
@RequestMapping("/tratamientos")
public class TratamientoController {

    private final TratamientoService service;

    public TratamientoController(TratamientoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<TratamientoDTO>>> obtenerTodos() {
        List<EntityModel<TratamientoDTO>> tratamientos = service.obtenerTodos().stream()
                .map(dto -> EntityModel.of(dto,
                        linkTo(methodOn(TratamientoController.class).obtenerPorId(dto.getId())).withSelfRel(),
                        linkTo(methodOn(TratamientoController.class).obtenerTodos()).withRel("tratamientos")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(tratamientos,
                linkTo(methodOn(TratamientoController.class).obtenerTodos()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TratamientoDTO>> obtenerPorId(@PathVariable Long id) {
        TratamientoDTO dto = service.obtenerPorId(id);
        return ResponseEntity.ok(EntityModel.of(dto,
                linkTo(methodOn(TratamientoController.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(TratamientoController.class).obtenerTodos()).withRel("tratamientos")));
    }

    @PostMapping
    public ResponseEntity<TratamientoDTO> crear(@Valid @RequestBody TratamientoDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TratamientoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody TratamientoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}