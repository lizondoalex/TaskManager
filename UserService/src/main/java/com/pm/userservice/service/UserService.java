package com.pm.userservice.service;

import com.pm.userservice.model.User;
import com.pm.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> findById(String  email){
        return userRepository.findById(email);
    }

    public void addUser(User user){
        userRepository.save(user);
    }
}
