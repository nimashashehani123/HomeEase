package com.example.homeease.Service;

import com.example.homeease.Entity.User;
import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    User updateUser(int id, User user);
    void deleteUser(int id);
}