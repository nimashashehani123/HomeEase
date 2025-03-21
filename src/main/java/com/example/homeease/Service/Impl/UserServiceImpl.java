package com.example.homeease.Service.Impl;

import com.example.homeease.Entity.User;
import com.example.homeease.Repo.UserRepository;
import com.example.homeease.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(int id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setAddress(user.getAddress());
            existingUser.setVerificationStatus(user.getVerificationStatus());
            existingUser.setIdProofPath(user.getIdProofPath());
            existingUser.setAddressProofPath(user.getAddressProofPath());
            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}