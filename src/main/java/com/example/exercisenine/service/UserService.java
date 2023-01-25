package com.example.exercisenine.service;

import com.example.exercisenine.entity.User;
import com.example.exercisenine.entity.enumiration.UserRole;
import com.example.exercisenine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return userRepository.findByLogin(authentication.getName());
    }

    public List<User> userList() {
        return userRepository.findAll();
    }

    public void saveUser(String login, String password, UserRole role) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
    }

    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public void editUser(Long userId, String login, String password, UserRole role) {
        User user = userRepository.findById(userId).get();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
    }

    public void blockUser(Long userId) {
        User user = userRepository.findById(userId).get();
        user.setRole(UserRole.BLOCK);
        userRepository.save(user);
    }
}
