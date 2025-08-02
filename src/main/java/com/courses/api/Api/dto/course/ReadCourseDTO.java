package com.courses.api.Api.dto.course;

import com.courses.api.Api.dto.ReadLessonDTO;
import com.courses.api.Api.dto.user.SimpleReadUserDTO;
import com.courses.api.Api.entity.Course;

import java.time.LocalDateTime;
import java.util.List;

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
        List<ReadLessonDTO> lessons,
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
                course.getLessons().stream().map(l -> new ReadLessonDTO(
                        l.getTitle(),
                        l.getURL(),
                        l.getDescription(),
                        l.getOrder(),
                        l.getCourse().getId()
                )).toList(),
                course.getCreatedAt(),
                course.getUpdatedAt());
    }
}
