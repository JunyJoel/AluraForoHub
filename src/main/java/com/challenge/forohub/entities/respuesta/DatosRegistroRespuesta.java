package com.challenge.forohub.entities.respuesta;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record DatosRegistroRespuesta(@NotBlank(message = "El mensaje no puede estar vacio") String mensaje,
                                     @NotBlank(message = "La fecha no puede estar vacia") Date fecha_creacion,
                                     @NotBlank(message = "El topico no puede estar vacio") Long topico_id,
                                     @NotBlank(message = "El autor no puede estar vacio") Long autor_id) {
}
