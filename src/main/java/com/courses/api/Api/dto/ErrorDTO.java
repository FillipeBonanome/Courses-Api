package com.courses.api.Api.dto;

import java.time.LocalDateTime;

public record ErrorDTO(
        int status,
        String message,
        String details,
        LocalDateTime timestamp
) {
}
