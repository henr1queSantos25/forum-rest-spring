package com.henr1que.forumspring.repository;

import com.henr1que.forumspring.domain.Course;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
    boolean existsByName(@NotBlank String name);
}
