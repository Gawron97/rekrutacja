package com.example.rekrutacja.utils.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FieldRequiredException extends RuntimeException {

    private final HttpStatus status;

    public FieldRequiredException(String message) {
        super(message);
        status = HttpStatus.BAD_REQUEST;
    }
}
