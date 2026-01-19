package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_EXISTED("error.user_existed", HttpStatus.CONFLICT),
    USER_NOT_FOUND("error.user_not_found", HttpStatus.NOT_FOUND),
    UNAUTHORIZED("error.unauththorized", HttpStatus.UNAUTHORIZED);

    private String message;
    private HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {

        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
