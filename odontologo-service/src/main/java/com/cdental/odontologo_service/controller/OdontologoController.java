package com.cdental.odontologo_service.controller;

import com.cdental.odontologo_service.dto.OdontologoDTO;
import com.cdental.odontologo_service.service.OdontologoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private final OdontologoService service;

    public OdontologoController(OdontologoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<OdontologoDTO>>> obtenerTodos() {
        List<EntityModel<OdontologoDTO>> odontologos = service.obtenerTodos().stream()
                .map(dto -> EntityModel.of(dto,
                        linkTo(methodOn(OdontologoController.class).obtenerPorId(dto.getId())).withSelfRel(),
                        linkTo(methodOn(OdontologoController.class).obtenerTodos()).withRel("odontologos")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(odontologos,
                linkTo(methodOn(OdontologoController.class).obtenerTodos()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<OdontologoDTO>> obtenerPorId(@PathVariable Long id) {
        OdontologoDTO dto = service.obtenerPorId(id);
        return ResponseEntity.ok(EntityModel.of(dto,
                linkTo(methodOn(OdontologoController.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(OdontologoController.class).obtenerTodos()).withRel("odontologos")));
    }

    @PostMapping
    public ResponseEntity<OdontologoDTO> crear(@Valid @RequestBody OdontologoDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OdontologoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody OdontologoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}