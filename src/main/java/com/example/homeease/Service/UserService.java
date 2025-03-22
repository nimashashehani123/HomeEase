package com.example.homeease.Service;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.UserDTO;

public interface UserService {
    ResponseDTO addUser(UserDTO userDTO);
    ResponseDTO updateUser(UserDTO userDTO);
    ResponseDTO deleteUser(int userId);
    ResponseDTO getAllUsers();
    ResponseDTO getUserById(int userId);
}