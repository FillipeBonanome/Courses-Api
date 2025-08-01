package com.courses.api.Api.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginUserDTO(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
