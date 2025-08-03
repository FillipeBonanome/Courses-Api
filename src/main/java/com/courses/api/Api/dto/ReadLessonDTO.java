package com.courses.api.Api.dto;

import com.courses.api.Api.dto.comment.ReadCommentDTO;

import java.util.List;

public record ReadLessonDTO(
        String title,
        String URL,
        String description,
        int order,
        Long courseId,
        List<ReadCommentDTO> comments
) {
}
