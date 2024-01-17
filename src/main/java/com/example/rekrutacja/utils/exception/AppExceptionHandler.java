package com.example.rekrutacja.utils.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<String> applicationNotFoundException(ApplicationNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(DocumentsUnapprovedException.class)
    public ResponseEntity<String> applicationNotFoundException(DocumentsUnapprovedException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(ApplicationDuplicationException.class)
    public ResponseEntity<String> applicationNotFoundException(ApplicationDuplicationException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<String> applicationNotFoundException(CandidateNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(CriteriaNotAchievedException.class)
    public ResponseEntity<String> applicationNotFoundException(CriteriaNotAchievedException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(MaturaExamNotFound.class)
    public ResponseEntity<String> applicationNotFoundException(MaturaExamNotFound ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(RecruitmentNotFoundException.class)
    public ResponseEntity<String> applicationNotFoundException(RecruitmentNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> applicationNotFoundException(LoginException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(FieldOfStudyNotFoundException.class)
    public ResponseEntity<String> applicationNotFoundException(FieldOfStudyNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(SpecializationNotFoundException.class)
    public ResponseEntity<String> applicationNotFoundException(SpecializationNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<String> applicationNotFoundException(FieldRequiredException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(BadActionException.class)
    public ResponseEntity<String> badActionException(BadActionException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(EmployeeNotAvailableException.class)
    public ResponseEntity<String> employeeUnavailableException(EmployeeNotAvailableException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}
