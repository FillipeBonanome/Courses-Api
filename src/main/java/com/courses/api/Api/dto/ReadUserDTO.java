package com.courses.api.Api.dto;

import com.courses.api.Api.entity.User;
import com.courses.api.Api.entity.UserRoles;

public record ReadUserDTO(
        String name,
        String username,
        String email,
        UserRoles userRole
) {
    public ReadUserDTO(User user) {
        this(user.getName(), user.getUsername(), user.getEmail(), user.getUserRole());
    }
}
