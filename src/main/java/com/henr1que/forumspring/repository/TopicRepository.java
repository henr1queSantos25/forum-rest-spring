package com.henr1que.forumspring.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.henr1que.forumspring.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByTitle(String title);

    boolean existsByMessage(String message);

    Page<Topic> findAllByStatusTrue(Pageable pageable);
}
