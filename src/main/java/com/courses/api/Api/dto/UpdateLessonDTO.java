package com.courses.api.Api.dto;

public record UpdateLessonDTO(
        String title,
        String URL,
        String description,
        Integer order
) {
}
