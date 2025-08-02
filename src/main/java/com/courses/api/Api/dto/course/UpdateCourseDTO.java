package com.courses.api.Api.dto.course;

public record UpdateCourseDTO(
        String title,
        String description,
        String category,
        String thumbnail,
        String slug
) {
}
