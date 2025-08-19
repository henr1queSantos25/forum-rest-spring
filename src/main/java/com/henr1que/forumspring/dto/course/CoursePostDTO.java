package com.henr1que.forumspring.dto.course;

import jakarta.validation.constraints.NotBlank;

public record CoursePostDTO(

        @NotBlank
        String name,

        @NotBlank
        String category) {
}
