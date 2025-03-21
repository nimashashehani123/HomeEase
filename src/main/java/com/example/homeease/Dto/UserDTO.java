package com.example.homeease.Dto;

import com.example.homeease.enums.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private int userId; // Unique identifier for the user

    private String name; // Name of the user

    private String email; // Email of the user (unique)

    private String password; // Password of the user

    private UserRole role; // Role of the user (e.g., CUSTOMER, SERVICE_PROVIDER)

    private String phoneNumber; // Contact number of the user

    private String address; // Address of the user

    private List<ProductDTO> products; // List of products added by the service provider (if applicable)

    private String verificationStatus; // Verification status of the user (e.g., Pending, Verified)

    private String idProofPath; // Path to the ID proof document (optional)

    private String addressProofPath; // Path to the address proof document (optional)
}