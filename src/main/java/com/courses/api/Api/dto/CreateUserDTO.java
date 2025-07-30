package com.courses.api.Api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
        @NotBlank
        String name,
        @NotBlank
        String username,
        @NotBlank
        String password,
        @Email @NotBlank
        String email
) {
}
