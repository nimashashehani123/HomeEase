package com.example.homeease.Dto;
import lombok.Data;

@Data
public class RegistrationDTO {
    private String name;
    private String email;
    private String password;
    private String role; // Customer, ServiceProvider, Admin
    private String phoneNumber;
    private String address;
}