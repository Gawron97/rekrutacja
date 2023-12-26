package com.example.rekrutacja.utils.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationDuplicationException extends RuntimeException {

    private final HttpStatus status;

    public ApplicationDuplicationException() {
        super("Application for field of study already exists");
        status = HttpStatus.CONFLICT;
    }

    public ApplicationDuplicationException(String message) {
        super(message);
        status = HttpStatus.CONFLICT;
    }

}
