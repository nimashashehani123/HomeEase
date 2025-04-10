package com.example.homeease.Service;

import com.example.homeease.Dto.LoginDTO;
import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.UserDTO;

public interface UserService {
    int addUser(UserDTO userDTO);
    ResponseDTO deleteUser(int userId);
    ResponseDTO getAllUsers();
    ResponseDTO getUserById(int userId);
    UserDTO searchUser(String username);
    ResponseDTO getUserIdByEmail(String email);
    ResponseDTO getAllServiceProviderIds();
    int changePassword(String token, String currentPassword, String newPassword);
    int updateUserPartial(UserDTO userDTO);
    ResponseDTO toggleUserStatus(int userId);
    ResponseDTO getUserDocuments(int userId);
    ResponseDTO updateVerificationStatus(int userId, String status);
}