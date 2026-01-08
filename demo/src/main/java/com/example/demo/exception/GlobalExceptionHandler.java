package com.example.demo.exception;

import com.example.demo.dto.res.ApiErrorResponse;
import com.example.demo.dto.res.ApiResponse;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        ApiResponse<Object> res = ApiResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("error.internal_server_error")
                .build();
        return ResponseEntity.internalServerError().body(res);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, InvalidDataAccessApiUsageException.class})
    public ResponseEntity<ApiResponse<Object>> handleDatabaseExceptions(Exception ex) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(ex);

        if (rootCause instanceof org.hibernate.TransientPropertyValueException) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT)
                    .body(ApiResponse.builder()
                            .status(HttpStatus.UNPROCESSABLE_CONTENT.value())
                            .message("error.unprocessable_content")
                            .build());
        }

        return ResponseEntity.badRequest()
                .body(ApiResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("database.error")
                        .build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handlingRuntimeException(RuntimeException ex) {
        ApiResponse<Object> res = ApiResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Object>> handlingAppException(AppException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ApiResponse<Object> res = ApiResponse.builder()
                .status(errorCode.getHttpStatus().value())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(errorCode.getHttpStatus().value()).body(res);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<Object>> handleResponseStatusException(ResponseStatusException ex) {
        ApiResponse<Object> res = ApiResponse.builder()
                .status(ex.getStatusCode().value())
                .message(ex.getReason())
                .build();
        return ResponseEntity.status(ex.getStatusCode()).body(res);
    }

    // check request params valid
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponse<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ApiResponse.builder().status(HttpStatus.BAD_REQUEST.value()).message("error.invalid_params_format").build();
    }

    // data validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<ApiErrorResponse.FieldErrorDetail> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> ApiErrorResponse.FieldErrorDetail.builder()
                        .field(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .toList();

        ApiErrorResponse response = ApiErrorResponse.builder()
                .status(HttpStatus.UNPROCESSABLE_CONTENT.value())
                .errors(errors)
                .build();

        return ResponseEntity.unprocessableContent().body(response);
    }

}
