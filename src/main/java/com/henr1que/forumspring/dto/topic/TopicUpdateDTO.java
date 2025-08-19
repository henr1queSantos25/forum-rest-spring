package com.henr1que.forumspring.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicUpdateDTO(

        String title,

        String message
) {
}
