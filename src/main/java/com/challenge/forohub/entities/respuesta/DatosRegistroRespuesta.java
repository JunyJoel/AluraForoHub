package com.challenge.forohub.entities.respuesta;

//import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record DatosRegistroRespuesta( String mensaje,
                                      Date fecha_creacion,
                                      Long topico_id,
                                      Long autor_id) {
}
