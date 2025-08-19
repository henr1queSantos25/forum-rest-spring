package com.henr1que.forumspring.repository;

import com.henr1que.forumspring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
