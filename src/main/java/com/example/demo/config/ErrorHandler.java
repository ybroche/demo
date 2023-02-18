package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorResponse> handle(Throwable ex) {
        log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex);
        return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handle(MethodArgumentNotValidException ex) {
        log.error(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex);
        return buildResponseErrorWithDetails(HttpStatus.BAD_REQUEST, ex);
    }

    private ResponseEntity<ApiErrorResponse> buildResponseErrorWithDetails(HttpStatus httpStatus, MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();
        for (FieldError error : ex.getFieldErrors()) {
            map.put(error.getField(), error.getDefaultMessage());
        }

        final var apiErrorResponse = ApiErrorResponse.builder().message(httpStatus.getReasonPhrase()).details(map).build();

        return new ResponseEntity<>(apiErrorResponse, httpStatus);
    }

    private ResponseEntity<ApiErrorResponse> buildResponseError(HttpStatus httpStatus) {


        final var apiErrorResponse = ApiErrorResponse.builder().message(httpStatus.getReasonPhrase()).build();

        return new ResponseEntity<>(apiErrorResponse, httpStatus);
    }


    @Builder
    @NonNull
    private static class ApiErrorResponse {

        @JsonProperty
        Map<String, String> details;
        @JsonProperty
        private String message;
    }
}
