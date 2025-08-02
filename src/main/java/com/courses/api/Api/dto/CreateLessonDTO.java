package com.courses.api.Api.dto;

public record CreateLessonDTO(
        String title,
        String URL,
        String description,
        int order,
        Long courseId
) {
}
