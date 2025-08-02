package com.courses.api.Api.service;

import com.courses.api.Api.dto.course.CreateCourseDTO;
import com.courses.api.Api.dto.course.ReadCourseDTO;
import com.courses.api.Api.dto.course.UpdateCourseDTO;
import com.courses.api.Api.dto.user.SimpleReadUserDTO;
import com.courses.api.Api.entity.Course;
import com.courses.api.Api.entity.User;
import com.courses.api.Api.entity.UserRoles;
import com.courses.api.Api.repository.CourseRepository;
import com.courses.api.Api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public ReadCourseDTO getCourseById(Long id) {
        Optional<Course> courseOptional = courseRepository.findByIdAndPublishedTrue(id);

        if (courseOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Course course = courseOptional.get();
        return new ReadCourseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getCategory(),
                course.getThumbnailFileName(),
                new SimpleReadUserDTO(course.getInstructor().getName()),
                course.getPublished(),
                course.getSlug(),
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }

    public ReadCourseDTO registerCourse(CreateCourseDTO courseDTO) {
        Optional<User> userOptional = userRepository.findById(courseDTO.instructorId());

        if (userOptional.isEmpty() || userOptional.get().getUserRole() == UserRoles.ROLE_USER) {
            throw new IllegalArgumentException("Invalid instructor");
        }

        User user = userOptional.get();

        if(!user.getActive()) {
            throw new IllegalArgumentException("Invalid instructor");
        }

        Course course = new Course(
                null,
                courseDTO.title(),
                courseDTO.description(),
                courseDTO.category(),
                courseDTO.thumbnail(),
                user,
                false,
                courseDTO.slug(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Course savedCourse = courseRepository.save(course);
        return new ReadCourseDTO(savedCourse);
    }

    public Page<ReadCourseDTO> getCourses(Pageable pageable) {
        return courseRepository.findAllByPublishedTrue(pageable).map(ReadCourseDTO::new);
    }

    public ReadCourseDTO deleteCourse(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if(courseOptional.isEmpty()) {
            throw new EntityNotFoundException("Course not found");
        }

        Course course = courseOptional.get();
        course.setPublished(false);
        Course savedCourse = courseRepository.save(course);
        return new ReadCourseDTO(savedCourse);
    }

    public ReadCourseDTO publishCourse(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if(courseOptional.isEmpty()) {
            throw new EntityNotFoundException("Course not found");
        }

        Course course = courseOptional.get();
        course.setPublished(true);
        Course savedCourse = courseRepository.save(course);
        return new ReadCourseDTO(savedCourse);
    }

    public ReadCourseDTO updateCourse(Long id, UpdateCourseDTO courseDTO) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (courseOptional.isEmpty()) {
            throw new EntityNotFoundException("Course not found");
        }

        Course course = courseOptional.get();
        course.updateCourse(courseDTO);

        Course savedCourse = courseRepository.save(course);
        return new ReadCourseDTO(savedCourse);
    }
}
