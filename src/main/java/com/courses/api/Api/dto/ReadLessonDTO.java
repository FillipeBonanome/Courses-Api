package com.courses.api.Api.dto;

public record ReadLessonDTO(
        String title,
        String URL,
        String description,
        int order,
        Long courseId
) {
}
