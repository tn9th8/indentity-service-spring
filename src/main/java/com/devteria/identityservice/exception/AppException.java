package com.devteria.identityservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {
    private ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        // super(errorCode.getError());
        super();
        this.errorCode = errorCode;
    }
}
