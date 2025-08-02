package com.courses.api.Api.controller;

import com.courses.api.Api.dto.CreateCourseDTO;
import com.courses.api.Api.dto.ReadCourseDTO;
import com.courses.api.Api.repository.CourseRepository;
import com.courses.api.Api.repository.UserRepository;
import com.courses.api.Api.service.CourseService;
import com.courses.api.Api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}-{slug}")
    public ResponseEntity<ReadCourseDTO> getCourseByIdAndSlug(@PathVariable(name = "id") Long id, @PathVariable(name = "slug") String slug) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadCourseDTO> getCourseById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasAuthority('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public ResponseEntity<ReadCourseDTO> createCourse(@Valid @RequestBody CreateCourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.registerCourse(courseDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadCourseDTO>> getCourses(@PageableDefault(size = 10, sort = {"title"}) Pageable pageable) {
        return ResponseEntity.ok(courseService.getCourses(pageable));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ReadCourseDTO> deleteCourse(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(courseService.deleteCourse(id));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public ResponseEntity<ReadCourseDTO> publicCourse(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(courseService.publishCourse(id));
    }

}
