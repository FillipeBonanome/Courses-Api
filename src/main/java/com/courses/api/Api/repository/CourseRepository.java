package com.courses.api.Api.repository;

import com.courses.api.Api.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByIdAndPublishedTrue(Long id);
    Page<Course> findAllByPublishedTrue(Pageable pageable);
}
