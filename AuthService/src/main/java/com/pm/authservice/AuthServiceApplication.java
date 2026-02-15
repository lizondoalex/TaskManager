package com.pm.authservice;

import com.pm.authservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthServiceApplication {

    private final UserService userService;

    public AuthServiceApplication(UserService userService){
        this.userService = userService;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AuthServiceApplication.class, args);
        PasswordEncoder encoder = ctx.getBean(PasswordEncoder.class);
        System.out.println("Hashed 'hola' = " + encoder.encode("pass"));
        System.out.println("DB_USER is " + System.getenv("DB_USER"));
    }

}
