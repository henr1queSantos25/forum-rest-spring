package com.henr1que.forumspring.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicPostDTO(

        @NotBlank
        String title,

        @NotBlank
        String message,

        @NotNull
        Long courseId
) {
}
