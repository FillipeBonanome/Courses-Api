package com.courses.api.Api.entity;

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


}
