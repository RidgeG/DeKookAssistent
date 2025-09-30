package com.ridge.geervliet.novi.dekookassistent.service;


import com.ridge.geervliet.novi.dekookassistent.dto.input.UserInputDto;
import com.ridge.geervliet.novi.dekookassistent.exception.ResourceNotFoundException;
import com.ridge.geervliet.novi.dekookassistent.model.User;
import com.ridge.geervliet.novi.dekookassistent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserInputDto inputDto) {
        User newUser = new User();
        newUser.setFirstName(inputDto.getFirstName());
        newUser.setLastName(inputDto.getLastName());
        newUser.setUsername(inputDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(inputDto.getPassword()));
        newUser.setEmail(inputDto.getEmail());
        newUser.setRole(inputDto.getRole()!= null? inputDto.getRole() : User.Role.USER);
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }


    @Transactional
    public void deleteByUsername(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }
        userRepository.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
