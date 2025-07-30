package com.courses.api.Api.dto;

public record CreateUserDTO(
        String name,
        String username,
        String password,
        String email
) {
}
