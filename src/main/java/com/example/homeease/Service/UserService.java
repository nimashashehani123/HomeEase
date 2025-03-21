package com.example.homeease.Service;

import com.example.homeease.Dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO loginUser(String email, String password);
    UserDTO updateUser(UserDTO userDTO);
    void deleteUser(int userId);
    UserDTO getUserById(int userId);
    List<UserDTO> getAllUsers();
    UserDTO verifyServiceProvider(int userId, String status);
}