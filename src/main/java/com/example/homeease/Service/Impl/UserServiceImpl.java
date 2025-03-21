package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.UserDTO;
import com.example.homeease.Entity.User;
import com.example.homeease.Repo.UserRepository;
import com.example.homeease.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setVerificationStatus("Pending");
        User savedUser = userRepository.save(user);
        return convertToUserDTO(savedUser);
    }

    @Override
    public UserDTO loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (user.getPassword().equals(password)) {
            return convertToUserDTO(user);
        }
        throw new RuntimeException("Invalid credentials");
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        User updatedUser = userRepository.save(user);
        return convertToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDTO getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return convertToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO verifyServiceProvider(int userId, String status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setVerificationStatus(status);
        User verifiedUser = userRepository.save(user);
        return convertToUserDTO(verifiedUser);
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(user.getAddress());
        userDTO.setVerificationStatus(user.getVerificationStatus());
        return userDTO;
    }
}