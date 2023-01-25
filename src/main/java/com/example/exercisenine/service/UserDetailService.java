package com.example.exercisenine.service;

import com.example.exercisenine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.exercisenine.entity.User user = userRepository.findByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("Unknown user login " + username);
        }
        return User.builder()
                .roles(user.getRole().name())
                .username(user.getLogin())
                .password(user.getPassword())
                .build();
    }
}
