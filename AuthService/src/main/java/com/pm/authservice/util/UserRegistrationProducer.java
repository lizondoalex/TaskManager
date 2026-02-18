package com.pm.authservice.util;

import com.pm.authservice.dto.KafkaRegisterDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationProducer {
    private static final String TOPIC = "user-registration";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public UserRegistrationProducer(KafkaTemplate<String, Object> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserRegistration(KafkaRegisterDTO register){
        System.out.println("Registration entered " + register.getId());
        kafkaTemplate.send(TOPIC, register);
    }
}
