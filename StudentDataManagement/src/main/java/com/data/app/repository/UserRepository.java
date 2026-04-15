package com.data.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.data.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
