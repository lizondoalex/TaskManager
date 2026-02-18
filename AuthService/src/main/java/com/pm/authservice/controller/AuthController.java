package com.pm.authservice.controller;

import com.pm.authservice.dto.*;
import com.pm.authservice.exceptions.EmailAlreadyExistsException;
import com.pm.authservice.service.AuthService;
import com.pm.authservice.util.RegisterResponse;
import com.pm.authservice.util.UserRegistrationProducer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
public class AuthController {

    private final AuthService authService;
    private final UserRegistrationProducer userRegistrationProducer;

    AuthController(AuthService authService, UserRegistrationProducer userRegistrationProducer){
        this.authService = authService;
        this.userRegistrationProducer = userRegistrationProducer;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){

        Optional<String> tokenOptional = authService.authenticate(loginRequestDTO);

        if(tokenOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = tokenOptional.get();
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){

        if(authService.validateEmail(registerRequestDTO.getEmail()))
            throw new EmailAlreadyExistsException("A member with this email already exists");

        Optional<RegisterResponse> optionalRegisterResponse = authService.register(registerRequestDTO);

        if (optionalRegisterResponse.isPresent()) {
            RegisterResponse registerResponse = optionalRegisterResponse.get();
            String token = registerResponse.getToken();
            UUID id = registerResponse.getId();

            KafkaRegisterDTO register = new KafkaRegisterDTO(
                    id,
                    registerRequestDTO.getEmail(),
                    registerRequestDTO.getName(),
                    registerRequestDTO.getAddress(),
                    registerRequestDTO.getDateOfBirth()
            );

            userRegistrationProducer.sendUserRegistration(register);

            return ResponseEntity.ok(new RegisterResponseDTO(token));
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authHeader){
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return authService.validateToken(authHeader.substring(7))
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
