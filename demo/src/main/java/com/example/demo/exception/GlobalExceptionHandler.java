package com.example.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<String> handlingRuntimeException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        String message = "error.invalid_params";

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("message", message);
        body.put("path", path);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            Map<String, String> errorDetail = new LinkedHashMap<>();

            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorDetail.put("field", fieldName);
            errorDetail.put("message", errorMessage);
            errors.add(errorDetail);
        });

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value()); // 422
        responseBody.put("errors", errors);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseBody);
    }

}
