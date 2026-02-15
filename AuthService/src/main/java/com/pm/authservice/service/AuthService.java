package com.pm.authservice.service;

import com.pm.authservice.dto.RegisterRequestDTO;
import com.pm.authservice.exceptions.EmailAlreadyExistsException;
import com.pm.authservice.model.User;
import com.pm.authservice.util.JwtUtil;
import com.pm.authservice.dto.LoginRequestDTO;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthService(PasswordEncoder passwordEncoder, UserService userService, JwtUtil jwtUtil){
        this.passwordEncoder  = passwordEncoder;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO){
        Optional<String> token = userService.findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(), u.getPassword()))
                .map( u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));

        return token;
    }

    public boolean validateToken(String token){
        try{
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException ex){
            return false;
        }
    }

    public boolean validateEmail(String email){
        Optional<User> user = userService.findByEmail(email);
        return user.isPresent();
    }

    public Optional<String> register(RegisterRequestDTO registerRequestDTO){

        if(validateEmail(registerRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Email " + registerRequestDTO.getEmail() + " already exists");
        }

        String hashedPassword = passwordEncoder.encode(registerRequestDTO.getPassword());
        User newUser = new User();
        newUser.setPassword(hashedPassword);
        newUser.setEmail(registerRequestDTO.getEmail());
        newUser.setRole("USER");

        userService.addUser(newUser);

        LoginRequestDTO login = new LoginRequestDTO();
        login.setEmail(registerRequestDTO.getEmail());
        login.setPassword(registerRequestDTO.getPassword());

        return authenticate(login);

    }

}
