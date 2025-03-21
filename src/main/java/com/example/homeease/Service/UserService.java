package com.example.homeease.Service;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> getAllUsers();
    User getUserById(int userId) throws ResourceNotFoundException;
    void deleteUser(int userId) throws ResourceNotFoundException;
    User updateUser(int userId, User user) throws ResourceNotFoundException;
}