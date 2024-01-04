package com.example.rekrutacja.utils.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FieldOfStudyNotFoundException extends RuntimeException {

    private final HttpStatus status;

    public FieldOfStudyNotFoundException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }
}
