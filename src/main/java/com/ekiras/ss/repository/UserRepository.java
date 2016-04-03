package com.ekiras.ss.repository;

import com.ekiras.ss.domain.Role;
import com.ekiras.ss.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author ekansh
 * @since 2/4/16
 */
public interface UserRepository extends CrudRepository<User,Long>{

    User findByUsername(String username);
    Set<Role> findRolesByUsername(String username);
}
