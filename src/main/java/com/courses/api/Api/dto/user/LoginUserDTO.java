package com.courses.api.Api.dto.user;

import jakarta.validation.constraints.NotBlank;

public record LoginUserDTO(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
