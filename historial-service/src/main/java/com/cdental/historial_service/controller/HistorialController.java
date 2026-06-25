package com.cdental.historial_service.controller;

import com.cdental.historial_service.dto.HistorialDTO;
import com.cdental.historial_service.service.HistorialService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/historiales")
public class HistorialController {

    private final HistorialService service;

    public HistorialController(HistorialService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<HistorialDTO>>> obtenerTodos() {
        List<EntityModel<HistorialDTO>> historiales = service.obtenerTodos().stream()
                .map(dto -> EntityModel.of(dto,
                        linkTo(methodOn(HistorialController.class).obtenerPorId(dto.getId())).withSelfRel(),
                        linkTo(methodOn(HistorialController.class).obtenerTodos()).withRel("historiales")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(historiales,
                linkTo(methodOn(HistorialController.class).obtenerTodos()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<HistorialDTO>> obtenerPorId(@PathVariable Long id) {
        HistorialDTO dto = service.obtenerPorId(id);
        return ResponseEntity.ok(EntityModel.of(dto,
                linkTo(methodOn(HistorialController.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(HistorialController.class).obtenerTodos()).withRel("historiales")));
    }   

    @PostMapping
    public ResponseEntity<HistorialDTO> crear(@Valid @RequestBody HistorialDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialDTO> actualizar(@PathVariable Long id, @Valid @RequestBody HistorialDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}