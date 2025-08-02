package com.courses.api.Api.controller;

import com.courses.api.Api.dto.CreateLessonDTO;
import com.courses.api.Api.dto.ReadLessonDTO;
import com.courses.api.Api.dto.UpdateLessonDTO;
import com.courses.api.Api.service.LessonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public ResponseEntity<ReadLessonDTO> registerLesson(@RequestBody @Valid CreateLessonDTO lessonDTO) {
        return ResponseEntity.ok(lessonService.registerLesson(lessonDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<ReadLessonDTO> getLessonById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    @PutMapping("{id}")
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public ResponseEntity<ReadLessonDTO> updateLesson(@PathVariable(name = "id") Long id, @RequestBody UpdateLessonDTO updateLessonDTO) {
        return  ResponseEntity.ok(lessonService.updateLesson(id, updateLessonDTO));
    }

}
