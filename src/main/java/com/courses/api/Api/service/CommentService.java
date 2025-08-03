package com.courses.api.Api.service;

import com.courses.api.Api.dto.comment.CreateCommentDTO;
import com.courses.api.Api.dto.comment.ReadCommentDTO;
import com.courses.api.Api.entity.Comment;
import com.courses.api.Api.entity.Lesson;
import com.courses.api.Api.entity.User;
import com.courses.api.Api.repository.CommentRepository;
import com.courses.api.Api.repository.LessonRepository;
import com.courses.api.Api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public ReadCommentDTO getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        Optional<User> user = userRepository.findByIdAndActiveTrue(comment.getUser().getId());
        String userName = user.isPresent() ? user.get().getName() : "[Deleted User]";
        return new ReadCommentDTO(
                comment.getContent(),
                userName,
                comment.getLesson().getTitle(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    public ReadCommentDTO postComment(UUID userId, CreateCommentDTO commentDTO) {
        User user = userRepository.findByIdAndActiveTrue(userId).orElseThrow(() -> new EntityNotFoundException("User not found while creating a comment"));
        if (!user.getActive()) {
            throw new EntityNotFoundException("User not found while creating a comment");
        }

        Lesson lesson = lessonRepository.findById(commentDTO.lessonId()).orElseThrow(() -> new EntityNotFoundException("Lesson not found while creating a comment"));

        Comment comment = new Comment(
                null,
                commentDTO.content(),
                user,
                lesson,
                false,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Comment savedComment = commentRepository.save(comment);
        return new ReadCommentDTO(
                savedComment.getContent(),
                savedComment.getUser().getName(),
                savedComment.getLesson().getTitle(),
                savedComment.getCreatedAt(),
                savedComment.getUpdatedAt()
        );
    }

    public ReadCommentDTO deleteComment(UUID userId, Long id) {
        User user = userRepository.findByIdAndActiveTrue(userId).orElseThrow(() -> new EntityNotFoundException("User not found while deleting a comment"));
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found for deletion"));
        comment.setContent("[Deleted]");
        comment.setDeleted(true);
        comment.setUpdatedAt(LocalDateTime.now());
        return new ReadCommentDTO(
                comment.getContent(),
                comment.getUser().getName(),
                comment.getLesson().getTitle(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
