package com.pm.userservice.util;

import com.pm.userservice.dto.KafkaRegisterDTO;
import com.pm.userservice.model.User;
import com.pm.userservice.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationListener {

    private final UserService userService;

    public UserRegistrationListener(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "user-registration", groupId = "user-service")
    public void consumeUserRegistration(KafkaRegisterDTO kafkaRegisterDTO){
        User newUser = new User();
        newUser.setId(kafkaRegisterDTO.getId());
        newUser.setName(kafkaRegisterDTO.getName());
        newUser.setEmail(kafkaRegisterDTO.getEmail());
        newUser.setAddress(kafkaRegisterDTO.getAddress());
        newUser.setDateOfBirth(kafkaRegisterDTO.getDateOfBirth());

        userService.addUser(newUser);
    }
}
