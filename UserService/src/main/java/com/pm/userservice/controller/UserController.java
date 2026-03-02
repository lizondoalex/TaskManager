package com.pm.userservice.controller;

import com.pm.userservice.dto.UserDTO;
import com.pm.userservice.model.User;
import com.pm.userservice.model.UserMapper;
import com.pm.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>>getUser(@RequestHeader("Authorization") String authorizationHeader){

        try{
            String token = authorizationHeader.substring(7);

            UUID uuid = userService.getUUID(token);
            System.out.println("uuid is " + uuid);
            Optional<User> optionalUser = userService.findByUUID(uuid);

            User user;
            if(optionalUser.isPresent()){
                user = optionalUser.get();
                System.out.println("user is " + user.getName());
            } else{
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }


            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(List.of(UserMapper.toDTO(user)));

        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

}
