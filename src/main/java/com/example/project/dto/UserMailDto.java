package com.example.project.dto;

public class UserMailDto {
    private String email;

    public UserMailDto() {

    }

    public UserMailDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
