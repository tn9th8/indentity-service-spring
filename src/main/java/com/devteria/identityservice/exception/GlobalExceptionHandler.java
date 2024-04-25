package com.devteria.identityservice.exception;

import com.devteria.identityservice.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

// ExceptionHandler(...) annotation: catch RuntimeException on all system (khi nao co RuntimeException, la se bay ve ham nay xu ly)
@ControllerAdvice // AOP (APO chi duoc kich hoat tu 1 lop khac
public class GlobalExceptionHandler {

    // exception that is uncategorized
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<String>> handleRuntimeException() {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED.getCode());
        apiResponse.setError(ErrorCode.UNCATEGORIZED.getError());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    // app exception that dev defines. dev will throw so that system catches
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<String>> handleAppException(AppException exception) {
        // get error code
        ErrorCode errorCode = exception.getErrorCode();
        // response
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setError(errorCode.getError());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    // exception that attribute is not valid. system auto catches
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        // get error code
        String ENUM_KEY = Objects.requireNonNull(exception.getFieldError()).getDefaultMessage(); // ~ exception.getFieldError().getDefaultMessage()
        ErrorCode errorCode = ErrorCode.KEY_STRING_INVALID;
        try {
            errorCode = ErrorCode.valueOf(ENUM_KEY);
        } catch (IllegalArgumentException e) {

        }
        // response
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setError(errorCode.getError());
        return ResponseEntity.badRequest().body(apiResponse);
    }

}
