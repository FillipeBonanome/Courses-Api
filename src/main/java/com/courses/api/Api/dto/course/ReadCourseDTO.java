package com.courses.api.Api.dto.course;

import com.courses.api.Api.dto.user.SimpleReadUserDTO;
import com.courses.api.Api.entity.Course;

import java.time.LocalDateTime;

//TODO --> add lessons
public record ReadCourseDTO(
        Long id,
        String title,
        String description,
        String category,
        String thumbnail,
        SimpleReadUserDTO instructor,
        Boolean published,
        String slug,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public ReadCourseDTO(Course course) {
        this(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getCategory(),
                course.getThumbnailFileName(),
                new SimpleReadUserDTO(course.getInstructor().getName()),
                course.getPublished(),
                course.getSlug(),
                course.getCreatedAt(),
                course.getUpdatedAt());
    }
}
