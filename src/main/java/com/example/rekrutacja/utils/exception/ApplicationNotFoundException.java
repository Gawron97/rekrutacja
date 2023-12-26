package com.example.rekrutacja.utils.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationNotFoundException extends RuntimeException {

    private final HttpStatus status;

    public ApplicationNotFoundException() {
        super("Application not found");
        status = HttpStatus.NOT_FOUND;
    }

    public ApplicationNotFoundException(String message) {
        super(message);
        status = HttpStatus.NOT_FOUND;
    }

}
