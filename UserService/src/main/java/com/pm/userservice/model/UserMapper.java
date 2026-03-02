package com.pm.userservice.model;

import com.pm.userservice.dto.UserDTO;

public class UserMapper {
    public static UserDTO toDTO(User user){
        return new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getAddress(), user.getDateOfBirth());
    }
}
