package com.example.rekrutacja.utils.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationDuplicationException extends RuntimeException {

    private final HttpStatus status;

    public ApplicationDuplicationException() {
        super("Nie mozna zlozyc drugiego podania na ten sam kierunek");
        status = HttpStatus.CONFLICT;
    }

    public ApplicationDuplicationException(String message) {
        super(message);
        status = HttpStatus.CONFLICT;
    }

}
