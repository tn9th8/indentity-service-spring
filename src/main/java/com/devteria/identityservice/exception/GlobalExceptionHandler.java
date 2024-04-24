package com.devteria.identityservice.exception;

import com.devteria.identityservice.dto.request.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    ApiResponse<String> apiResponse;

    // exception that client's request is bad
    // ExceptionHandler(...) annotation: catch RuntimeException on all system (khi nao co RuntimeException, la se bay ve ham nay xu ly)
    // Output is badRequest, 400, y nghia la loi cua user
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<String>> handleRuntimeException(RuntimeException exception) {
        this.apiResponse.setError(exception.getMessage());
        return ResponseEntity.badRequest().body(this.apiResponse);
    }

    // exception that attribute is not valid
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(Objects.requireNonNull(exception.getFieldError()).getDefaultMessage());
        // return ResponseEntity.badRequest().body(exception.getFieldError().getDefaultMessage());
    }
}
