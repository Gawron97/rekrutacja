package com.example.rekrutacja.utils.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CandidateNotFoundException extends RuntimeException {

    private final HttpStatus status;

    public CandidateNotFoundException() {
        super("Candidate not found");
        status = HttpStatus.NOT_FOUND;
    }

    public CandidateNotFoundException(String message) {
        super(message);
        status = HttpStatus.NOT_FOUND;
    }
}
