package com.example.exercisenine.repository;

import com.example.exercisenine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);


    Optional<User> findById(Long userId);
}
