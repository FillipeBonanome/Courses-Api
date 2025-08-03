package com.courses.api.Api.dto.user;

import com.courses.api.Api.entity.User;
import com.courses.api.Api.entity.UserRoles;

import java.util.UUID;

public record ReadUserDTO(
        UUID id,
        String name,
        String username,
        String email,
        UserRoles userRole
) {
    public ReadUserDTO(User user) {
        this(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getUserRole());
    }
}
