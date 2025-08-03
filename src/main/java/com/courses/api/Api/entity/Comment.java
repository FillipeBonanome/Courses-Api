package com.courses.api.Api.entity;

import com.courses.api.Api.dto.comment.UpdateCommentDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;
    private Boolean deleted;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public void updateComment(UpdateCommentDTO commentDTO) {
        if (commentDTO.content() != null) {
            this.content = commentDTO.content();
        }
        this.updatedAt = LocalDateTime.now();
    }
}
