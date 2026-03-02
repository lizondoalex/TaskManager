package com.pm.authservice.dto;

import java.time.LocalDate;
import java.util.UUID;

public class KafkaRegisterDTO {

    private UUID id;

    private String email;

    private String name;

    private String address;

    private LocalDate dateOfBirth;

    public KafkaRegisterDTO(){}

    public KafkaRegisterDTO(UUID id, String email, String name, String address, LocalDate dateOfBirth) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
