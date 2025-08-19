package com.henr1que.forumspring.domain;


import com.henr1que.forumspring.dto.topic.TopicPostDTO;
import com.henr1que.forumspring.dto.topic.TopicUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String message;

    private LocalDateTime created_at;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Topic(@NotBlank String title, @NotBlank String message, @NotNull Course course) {
        this.title = title;
        this.message = message;
        this.created_at = LocalDateTime.now();
        this.status = true;
        this.course = course;
    }

    public void updateInformations(@Valid TopicUpdateDTO topicUpdateDTO) {
        if (topicUpdateDTO.title() != null) {
            this.title = topicUpdateDTO.title();
        }
        if (topicUpdateDTO.message() != null) {
            this.message = topicUpdateDTO.message();
        }
    }

    public void delete() {
        this.status = false;
    }
}
