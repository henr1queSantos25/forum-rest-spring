package com.henr1que.forumspring.repository;

import com.henr1que.forumspring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {

    UserDetails findByEmail(String email);
}
