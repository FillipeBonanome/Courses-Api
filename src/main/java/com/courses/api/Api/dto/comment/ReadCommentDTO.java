package com.courses.api.Api.dto.comment;

import java.time.LocalDateTime;

public record ReadCommentDTO(
        String content,
        String username,
        String lessonName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
