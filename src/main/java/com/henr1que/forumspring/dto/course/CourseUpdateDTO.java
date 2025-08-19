package com.henr1que.forumspring.dto.course;

import jakarta.validation.constraints.NotBlank;

public record CourseUpdateDTO(
        String name,

        String category) {
}
