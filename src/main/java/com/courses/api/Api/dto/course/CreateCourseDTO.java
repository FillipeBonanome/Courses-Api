package com.courses.api.Api.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCourseDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String category,
        String thumbnail,
        @NotNull
        Long instructorId,
        @NotBlank
        String slug
) {
}
