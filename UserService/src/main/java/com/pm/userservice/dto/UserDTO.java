package com.pm.userservice.dto;

import java.time.LocalDate;
import java.util.UUID;

public class UserDTO {

    private UUID id;
    private String email;
    private String name;
    private String address;
    private LocalDate dateOfBirth;

    public UserDTO(UUID id, String email, String name, String address, LocalDate dateOfBirth) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

