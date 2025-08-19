package com.henr1que.forumspring.dto.topic;

import com.henr1que.forumspring.domain.Topic;

import java.time.LocalDateTime;

public record TopicDetailsDTO(String title, String message, LocalDateTime createdAt) {
    public TopicDetailsDTO(Topic topic) {
        this(topic.getTitle(), topic.getMessage(), topic.getCreated_at());
    }
}
