package com.ekiras.ss.repository;

import com.ekiras.ss.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ekansh
 * @since 2/4/16
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
