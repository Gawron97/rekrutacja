package com.example.rekrutacja.utils.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MaturaExamNotFound extends RuntimeException {

    private final HttpStatus status;

    public MaturaExamNotFound() {
        super("Matura exam not found");
        status = HttpStatus.NOT_FOUND;
    }

    public MaturaExamNotFound(String message) {
        super(message);
        status = HttpStatus.NOT_FOUND;
    }

}
