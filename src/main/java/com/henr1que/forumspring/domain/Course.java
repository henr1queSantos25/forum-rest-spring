package com.henr1que.forumspring.domain;


import com.henr1que.forumspring.dto.course.CoursePostDTO;
import com.henr1que.forumspring.dto.course.CourseUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "courses")
@Entity(name = "Course")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Topic> topics;

    public Course(CoursePostDTO data) {
        this.name = data.name();
        this.category = data.category();
    }

    public void updateInformations(@Valid CourseUpdateDTO courseUpdateDTO) {
        if (courseUpdateDTO.name() != null) {
            this.name = courseUpdateDTO.name();
        }
        if (courseUpdateDTO.category() != null) {
            this.category = courseUpdateDTO.category();
        }
    }
}
