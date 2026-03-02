package com.pm.userservice.service;

import com.pm.userservice.model.User;
import com.pm.userservice.repository.UserRepository;
import com.pm.userservice.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public UserService(JwtUtil jwtUtil, UserRepository userRepository){
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    public Optional<User> findByUUID(UUID uuid){
        return userRepository.findById(uuid);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public UUID getUUID(String token){
        return jwtUtil.getUUID(token);
    }
}
