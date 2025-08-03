package com.courses.api.Api.controller;

import com.courses.api.Api.dto.comment.CreateCommentDTO;
import com.courses.api.Api.dto.comment.ReadCommentDTO;
import com.courses.api.Api.entity.User;
import com.courses.api.Api.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("{id}")
    public ResponseEntity<ReadCommentDTO> getCommentById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReadCommentDTO> postComment(Authentication authentication, @RequestBody @Valid CreateCommentDTO commentDTO) {
        var authorities = authentication.getAuthorities();
        //TODO --> Refactor
        var userOptional = (Optional<User>) authentication.getPrincipal();
        if(userOptional.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        User user = userOptional.get();
        return ResponseEntity.ok(commentService.postComment(user.getId(), commentDTO));
    }

}
