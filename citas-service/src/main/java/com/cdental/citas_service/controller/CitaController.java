package com.cdental.citas_service.controller;

import com.cdental.citas_service.dto.CitaDTO;
import com.cdental.citas_service.service.CitaService;
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
    public ResponseEntity<EntityModel<CitaDTO>> crear(@Valid @RequestBody CitaDTO dto) {
        CitaDTO nuevo = service.crear(dto);
        return new ResponseEntity<>(EntityModel.of(nuevo,
                linkTo(methodOn(CitaController.class).obtenerPorId(nuevo.getId())).withSelfRel(),
                linkTo(methodOn(CitaController.class).obtenerTodas()).withRel("citas")), 
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CitaDTO>> actualizar(@PathVariable Long id, @RequestBody CitaDTO dto) {
        CitaDTO modificado = service.actualizar(id, dto);
        return ResponseEntity.ok(EntityModel.of(modificado,
                linkTo(methodOn(CitaController.class).obtenerPorId(modificado.getId())).withSelfRel(),
                linkTo(methodOn(CitaController.class).obtenerTodas()).withRel("citas")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}