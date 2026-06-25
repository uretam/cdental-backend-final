package com.cdental.odontologo_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class OdontologoDTO extends RepresentationModel<OdontologoDTO> {
    
    private Long id;

    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(regexp = "^[0-9]{1,2}\\.[0-9]{3}\\.[0-9]{3}-[0-9kK]$", message = "El RUT debe tener un formato válido (ej: 12.345.678-9)")
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre {min} y {max} caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ. ]+$", message = "El nombre solo debe contener letras, puntos y espacios")
    private String nombre;

    @NotBlank(message = "La especialidad es obligatorio")
    @Size(min = 3, max = 50, message = "La especialidad debe tener entre {min} y {max} caracteres")
    private String especialidad;
}