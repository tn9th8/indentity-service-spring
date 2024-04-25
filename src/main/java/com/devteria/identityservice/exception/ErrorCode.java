package com.devteria.identityservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED(400, "uncategorized exception"),
    USER_EXIST(409, "user is existed"), // client error - conflict
    USER_NOT_FOUND(404, "user not found"), // client error - not found
    USERNAME_INVALID(400, "username must be at least 3 characters"), // client error - bad request
    PASSWORD_INVALID(400, "password must be at least 6 characters"), // client error - bad request
    KEY_STRING_INVALID(501, "enum key string is invalid") // server error - not implemented
    ;

    private int code;
    private String error;

    ErrorCode(int code, String error) {
        this.code = code;
        this.error = error;
    }
}
