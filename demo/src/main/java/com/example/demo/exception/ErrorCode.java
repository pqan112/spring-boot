package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION("error.uncategorized_error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED("error.user_existed", HttpStatus.CONFLICT),
    USER_NOT_FOUND("error.user_not_found", HttpStatus.NOT_FOUND),
    UNAUTHORIZED("error.do_not_have_permission", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED( "error.unauthenticated", HttpStatus.UNAUTHORIZED);

    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
