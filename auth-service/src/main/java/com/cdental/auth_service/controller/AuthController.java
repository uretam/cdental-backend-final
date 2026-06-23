package com.cdental.auth_service.controller;

import com.cdental.auth_service.dto.AuthResponseDTO;
import com.cdental.auth_service.dto.LoginRequestDTO;
import com.cdental.auth_service.dto.UsuarioDTO;
import com.cdental.auth_service.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService service;

    public AuthController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(service.login(request));
    }

    @GetMapping("/users")
    public ResponseEntity<CollectionModel<EntityModel<UsuarioDTO>>> obtenerTodos() {
        List<EntityModel<UsuarioDTO>> usuarios = service.obtenerTodos().stream()
                .map(dto -> EntityModel.of(dto,
                        linkTo(methodOn(AuthController.class).obtenerPorId(dto.getId())).withSelfRel(),
                        linkTo(methodOn(AuthController.class).obtenerTodos()).withRel("usuarios")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(usuarios, 
                linkTo(methodOn(AuthController.class).obtenerTodos()).withSelfRel()));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<UsuarioDTO>> obtenerPorId(@PathVariable Long id) {
        UsuarioDTO dto = service.obtenerPorId(id);
        
        return ResponseEntity.ok(EntityModel.of(dto,
                linkTo(methodOn(AuthController.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(AuthController.class).obtenerTodos()).withRel("usuarios")));
    }
}