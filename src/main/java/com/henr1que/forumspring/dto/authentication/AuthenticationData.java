package com.henr1que.forumspring.dto.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationData(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String password) {
}
