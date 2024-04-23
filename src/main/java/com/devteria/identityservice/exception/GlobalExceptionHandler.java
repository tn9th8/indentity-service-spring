package com.devteria.identityservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ExceptionHandler(...) annotation: catch RuntimeException on all system (khi nao co RuntimeException, la se bay ve ham nay xu ly)
    // Output is badRequest, 400, y nghia la loi cua user
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
