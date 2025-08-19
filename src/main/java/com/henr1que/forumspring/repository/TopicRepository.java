package com.henr1que.forumspring.repository;

import com.henr1que.forumspring.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByTitle(String title);

    boolean existsByMessage(String message);
}
