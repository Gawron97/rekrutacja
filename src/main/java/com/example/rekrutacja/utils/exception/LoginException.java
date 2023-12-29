package com.example.rekrutacja.utils.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LoginException extends RuntimeException {

    private final HttpStatus status;

    public LoginException(String message) {
        super(message);
        status = HttpStatus.UNAUTHORIZED;
    }

    public LoginException() {
        this("Login failed");
    }
}
