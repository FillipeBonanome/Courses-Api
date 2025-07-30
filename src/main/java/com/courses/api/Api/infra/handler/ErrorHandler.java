package com.courses.api.Api.infra.handler;

import com.courses.api.Api.dto.ErrorDTO;
import com.courses.api.Api.infra.exception.DuplicateResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorDTO> handleDuplicateResourceException(DuplicateResourceException exception, WebRequest request) {
        ErrorDTO error = new ErrorDTO(
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
