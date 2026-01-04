package com.example.demo.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {
    private int status;
    private String message;
    private List<FieldErrorDetail> errors; // Danh sách các field bị lỗi

    @Getter
    @Setter
    @Builder
    public static class FieldErrorDetail {
        private String field;
        private String message;
    }
}
