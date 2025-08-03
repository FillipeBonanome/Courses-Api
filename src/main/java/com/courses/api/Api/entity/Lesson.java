package com.courses.api.Api.entity;

import com.courses.api.Api.dto.UpdateLessonDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String URL;
    @NotBlank
    private String description;
    @NotNull
    @Column(name = "lesson_order")
    private int order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Comment> commentList = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void updateLesson(UpdateLessonDTO lessonDTO) {
        if (lessonDTO.title() != null) {
            this.title = lessonDTO.title();
        }
        if (lessonDTO.URL() != null) {
            this.URL = lessonDTO.URL();
        }
        if (lessonDTO.description() != null) {
            this.description = lessonDTO.description();
        }
        if(lessonDTO.order() != null) {
            this.order = lessonDTO.order();
        }
        this.updatedAt = LocalDateTime.now();
    }
}
