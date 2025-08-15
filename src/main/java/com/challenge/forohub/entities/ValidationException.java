package com.challenge.forohub.entities;

public class ValidationException extends RuntimeException{
    public ValidationException(String mensaje) {
        super(mensaje);
    }
}
