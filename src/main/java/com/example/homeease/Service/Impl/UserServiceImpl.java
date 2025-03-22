package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.UserDTO;
import com.example.homeease.Entity.User;
import com.example.homeease.Repo.UserRepository;
import com.example.homeease.Service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseDTO addUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return new ResponseDTO(400, "User already exists with email: " + userDTO.getEmail(), null);
        }

        // Map UserDTO to User entity
        User user = modelMapper.map(userDTO, User.class);

        // Set default values (if any)
        if (user.getVerificationStatus() == null) {
            user.setVerificationStatus("Pending"); // Example default value
        }
        // Save the user
        User savedUser = userRepository.save(user);

        // Map the saved user back to DTO
        UserDTO savedUserDTO = modelMapper.map(savedUser, UserDTO.class);

        // Return success response
        return new ResponseDTO(200, "User added successfully", savedUserDTO);

    }

    @Override
    public ResponseDTO updateUser(UserDTO userDTO) {
        if (!userRepository.existsById(userDTO.getUserId())) {
            return new ResponseDTO(404, "User not found with id: " + userDTO.getUserId(), null);
        }
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return new ResponseDTO(200, "User updated successfully", userDTO);
    }

    @Override
    public ResponseDTO deleteUser(int userId) {
        if (!userRepository.existsById(userId)) {
            return new ResponseDTO(404, "User not found with id: " + userId, null);
        }
        userRepository.deleteById(userId);
        return new ResponseDTO(200, "User deleted successfully", null);
    }

    @Override
    public ResponseDTO getAllUsers() {
        List<UserDTO> userList = modelMapper.map(userRepository.findAll(),
                new TypeToken<List<UserDTO>>() {}.getType());
        return new ResponseDTO(200, "Users retrieved successfully", userList);
    }

    @Override
    public ResponseDTO getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new ResponseDTO(200, "User retrieved successfully", userDTO);
    }
}