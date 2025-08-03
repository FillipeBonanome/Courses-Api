package com.courses.api.Api.controller;

import com.courses.api.Api.dto.user.FullUserDTO;
import com.courses.api.Api.entity.User;
import com.courses.api.Api.infra.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping
    public ResponseEntity<FullUserDTO> viewProfile(Authentication authentication) {
        var userOptional = (Optional<User>) authentication.getPrincipal();
        if (userOptional.isEmpty()) {
            throw new UserException("User not found");
        }

        return ResponseEntity.ok(new FullUserDTO(userOptional.get()));
    }

}
