package com.example.demo.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
    @ExceptionHandler(DuplicateRequirementException.class)
    public ResponseEntity<String> handleDuplicateRequirementException(DuplicateRequirementException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(DuplicateTrainerException.class)
    public ResponseEntity<String> handleDuplicateTrainerException(DuplicateTrainerException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(RequirementDeletionException.class)
    public ResponseEntity<String> handleRequirementDeletionException(RequirementDeletionException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(TrainerDeletionException.class)
    public ResponseEntity<String> handleTrainerDeletionException(TrainerDeletionException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> duplictaeEmailHandler(DuplicateEmailException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }
    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<String> duplictaeUserHandler(DuplicateUserException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
