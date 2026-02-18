package com.pm.authservice.util;

import java.util.UUID;

public class RegisterResponse {

    public RegisterResponse(UUID id, String token){
        this.id = id;
        this.token = token;
    }

    private UUID id;
    private String token;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
