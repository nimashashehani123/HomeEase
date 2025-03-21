package com.example.homeease.Dto;
import com.example.homeease.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {
    private int userId;
    private String name;
    private String email;
    private String password;
    private UserRole role; // Customer, ServiceProvider, Admin
    private String phoneNumber;
    private String address;
    private String verificationStatus; // Pending, Verified, Rejected
    private String idProofPath; // Path to ID proof document
    private String addressProofPath; // Path to address proof document
}