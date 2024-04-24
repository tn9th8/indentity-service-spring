package com.devteria.identityservice.exception;

public enum ErrorCode {
    UNCATEGORIZED(400, "uncategorized exception"),
    USER_EXIST(409, "user is existed"), // client error - conflict
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
