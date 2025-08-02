package com.courses.api.Api.entity;

import com.courses.api.Api.dto.course.UpdateCourseDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String category;

    @Column(name = "thumbnail")
    private String thumbnailFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private User instructor;
    private Boolean published;
    private String slug;
    //TODO --> add lessons
    //List<Lesson> lessons = new ArrayList<>()
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public void updateCourse(UpdateCourseDTO courseDTO) {
        if (courseDTO.title() != null) {
            this.title = courseDTO.title();
        }
        if (courseDTO.description() != null) {
            this.description = courseDTO.description();
        }
        if (courseDTO.category() != null) {
            this.category = courseDTO.category();
        }
        if (courseDTO.thumbnail() != null) {
            this.thumbnailFileName = courseDTO.thumbnail();
        }
        if(courseDTO.slug() != null) {
            this.slug = courseDTO.slug();
        }
        this.updatedAt = LocalDateTime.now();
    }
}
