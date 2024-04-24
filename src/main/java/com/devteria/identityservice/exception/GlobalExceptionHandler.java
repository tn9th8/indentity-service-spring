package com.devteria.identityservice.exception;

import com.devteria.identityservice.dto.request.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

// ExceptionHandler(...) annotation: catch RuntimeException on all system (khi nao co RuntimeException, la se bay ve ham nay xu ly)
@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    ApiResponse<String> apiResponse;

    // exception that is uncategorized
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<String>> handleRuntimeException(RuntimeException exception) {
        this.apiResponse.setCode(ErrorCode.UNCATEGORIZED.getCode());
        this.apiResponse.setError(ErrorCode.UNCATEGORIZED.getError());
        return ResponseEntity.badRequest().body(this.apiResponse);
    }

    // app exception that dev defines. dev will throw so that system catches
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<String>> handleAppException(AppException exception) {
        // get error code
        ErrorCode errorCode = exception.getErrorCode();
        // response
        this.apiResponse.setCode(errorCode.getCode());
        this.apiResponse.setError(errorCode.getError());
        return ResponseEntity.badRequest().body(this.apiResponse);
    }

    // exception that attribute is not valid. system auto catches
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        // get error code
        String ENUM_KEY = Objects.requireNonNull(exception.getFieldError()).getDefaultMessage(); // ~ exception.getFieldError().getDefaultMessage()
        // ErrorCode errorCode = ErrorCode.valueOf(ENUM_KEY);
        ErrorCode errorCode = ErrorCode.KEY_STRING_INVALID;
        try {
            errorCode = ErrorCode.valueOf(ENUM_KEY);
        } catch (IllegalArgumentException e) {
            // throw new IllegalArgumentException();
        }
        // response
        this.apiResponse.setCode(errorCode.getCode());
        this.apiResponse.setError(errorCode.getError());
        return ResponseEntity.badRequest().body(this.apiResponse);
    }

    // ko on
    @ExceptionHandler(value = IllegalArgumentException.class)
    ResponseEntity<ApiResponse<String>> handleIllegalArgumentException() {
        // response
        this.apiResponse.setCode(ErrorCode.KEY_STRING_INVALID.getCode());
        this.apiResponse.setError(ErrorCode.KEY_STRING_INVALID.getError());
        return ResponseEntity.badRequest().body(this.apiResponse);
    }
}
