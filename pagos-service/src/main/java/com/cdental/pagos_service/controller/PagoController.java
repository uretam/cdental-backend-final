package com.cdental.pagos_service.controller;

import com.cdental.pagos_service.dto.PagoDTO;
import com.cdental.pagos_service.service.PagoService;
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
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService service;

    public PagoController(PagoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<PagoDTO>>> obtenerTodos() {
        List<EntityModel<PagoDTO>> pagos = service.obtenerTodos().stream()
                .map(dto -> EntityModel.of(dto,
                        linkTo(methodOn(PagoController.class).obtenerPorId(dto.getId())).withSelfRel(),
                        linkTo(methodOn(PagoController.class).obtenerTodos()).withRel("pagos")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(pagos,
                linkTo(methodOn(PagoController.class).obtenerTodos()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PagoDTO>> obtenerPorId(@PathVariable Long id) {
        PagoDTO dto = service.obtenerPorId(id);
        return ResponseEntity.ok(EntityModel.of(dto,
                linkTo(methodOn(PagoController.class).obtenerPorId(dto.getId())).withSelfRel(),
                linkTo(methodOn(PagoController.class).obtenerTodos()).withRel("pagos")));
    }

    @PostMapping
    public ResponseEntity<PagoDTO> crear(@Valid @RequestBody PagoDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PagoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}