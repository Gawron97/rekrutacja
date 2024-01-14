package com.example.rekrutacja.utils.exception;

import org.springframework.http.HttpStatus;

public class BadActionException extends AppException {
    public BadActionException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
