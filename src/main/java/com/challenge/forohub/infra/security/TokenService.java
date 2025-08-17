package com.challenge.forohub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.challenge.forohub.entities.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}") //Tomar el valor de la variable de entorno y colocarlo en secret
    private String secret; //variable para encriptar el tocken
    private static final String ISSUER = "api.forohub.com";

    public String generarToken(Usuario usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret); //se selecciona el algoritmo de encriptación para el token usando la variable secret
            return JWT.create() //
                    .withIssuer(ISSUER) //se agrega la información del emisor, debe ser igual en el Metodo getSubject
                    .withSubject(usuario.getNombre()) //se agrega el username
                    .withExpiresAt(fechaExpiracion()) //se agrega la fecha de expiración
                    .sign(algoritmo); //se encripta el token usando el algoritmo seleccionado
        } catch (JWTCreationException exception){ //en caso de no poder crear el token
            throw new RuntimeException("error al generar el token JWT",exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido: " + tokenJWT, exception);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-06:00"));
    }
}
