package com.courses.api.Api.dto.user;

import com.courses.api.Api.entity.User;
import com.courses.api.Api.entity.UserRoles;

import java.time.LocalDateTime;
import java.util.UUID;

public record FullUserDTO(
    UUID id,
    String name,
    String username,
    String email,
    UserRoles userRoles,
    Boolean active,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public FullUserDTO(User user) {
        this(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getUserRole(), user.getActive(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
