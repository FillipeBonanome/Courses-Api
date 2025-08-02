package com.courses.api.Api.service;

import com.courses.api.Api.dto.CreateLessonDTO;
import com.courses.api.Api.dto.ReadLessonDTO;
import com.courses.api.Api.entity.Course;
import com.courses.api.Api.entity.Lesson;
import com.courses.api.Api.repository.CourseRepository;
import com.courses.api.Api.repository.LessonRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    //TODO --> Sanitize description for markdown
    public ReadLessonDTO registerLesson(@Valid CreateLessonDTO lessonDTO) {
        Optional<Course> courseOptional = courseRepository.findById(lessonDTO.courseId());

        if (courseOptional.isEmpty()) {
            throw new EntityNotFoundException("Course not found to create lesson");
        }

        Course course = courseOptional.get();
        Lesson lesson = new Lesson(
                null,
                lessonDTO.title(),
                lessonDTO.URL(),
                lessonDTO.description(),
                lessonDTO.order(),
                course,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Lesson savedLesson = lessonRepository.save(lesson);
        return new ReadLessonDTO(
                savedLesson.getTitle(),
                savedLesson.getURL(),
                savedLesson.getDescription(),
                savedLesson.getOrder(),
                savedLesson.getCourse().getId()
        );

    }

    public ReadLessonDTO getLessonById(Long id) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(id);

        if (optionalLesson.isEmpty()) {
            throw new EntityNotFoundException("Lesson not found");
        }

        Lesson lesson = optionalLesson.get();

        if (!lesson.getCourse().getPublished()) {
            throw new EntityNotFoundException("Lesson not found");
        }

        return new ReadLessonDTO(
                lesson.getTitle(),
                lesson.getURL(),
                lesson.getDescription(),
                lesson.getOrder(),
                lesson.getCourse().getId()
        );

    }
}
