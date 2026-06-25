package com.cdental.auth_service.service;

import com.cdental.auth_service.config.JwtUtil;
import com.cdental.auth_service.dto.AuthResponseDTO;
import com.cdental.auth_service.dto.LoginRequestDTO;
import com.cdental.auth_service.dto.UsuarioDTO;
import com.cdental.auth_service.exception.AuthException;
import com.cdental.auth_service.model.Usuario;
import com.cdental.auth_service.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository repository;
    private final JwtUtil jwtUtil;

    public UsuarioService(UsuarioRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        logger.info("Procesando intento de inicio de sesion para el usuario: {}", request.getUsername());

        Usuario usuario = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AuthException("Credenciales incorrectas"));

        if (!usuario.getPassword().equals(request.getPassword())) {
            logger.warn("Contrasena incorrecta para el usuario: {}", request.getUsername());
            throw new AuthException("Credenciales incorrectas");
        }

        String token = jwtUtil.generateToken(usuario.getUsername(), usuario.getRole());
        logger.info("Token generado exitosamente para: {}", request.getUsername());
        return new AuthResponseDTO(token, usuario.getUsername(), usuario.getRole());
    }

    public List<UsuarioDTO> obtenerTodos() {
        logger.info("Consultando todos los usuarios en la base de datos");
        return repository.findAll().stream()
                .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getUsername(), usuario.getRole()))
                .collect(Collectors.toList());
    }

    public UsuarioDTO obtenerPorId(Long id) {
        logger.info("Buscando usuario por ID: {}", id);
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new AuthException("Usuario no encontrado con ID: " + id));
        return new UsuarioDTO(usuario.getId(), usuario.getUsername(), usuario.getRole());
    }
}