package com.example.rekrutacja.utils.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DocumentsUnapprovedException extends RuntimeException {

    private final HttpStatus status;

    public DocumentsUnapprovedException() {
        super("Nie udalo się złożyć podania z powodu niezatwierdzonych dokumentów");
        status = HttpStatus.CONFLICT;
    }

    public DocumentsUnapprovedException(String message) {
        super(message);
        status = HttpStatus.CONFLICT;
    }

}
