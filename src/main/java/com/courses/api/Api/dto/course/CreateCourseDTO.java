package com.courses.api.Api.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateCourseDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String category,
        String thumbnail,
        @NotNull
        UUID instructorId,
        @NotBlank
        String slug
) {
}
