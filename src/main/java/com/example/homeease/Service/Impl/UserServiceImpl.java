package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.UserDTO;
import com.example.homeease.Entity.User;
import com.example.homeease.Repo.UserRepository;
import com.example.homeease.Service.UserService;
import com.example.homeease.Utill.VarList;
import com.example.homeease.enums.UserRole;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return modelMapper.map(user,UserDTO.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return authorities;
    }

    @Override
    public UserDTO searchUser(String username) {
        if (userRepository.existsByEmail(username)) {
            User user=userRepository.findByEmail(username);
            return modelMapper.map(user,UserDTO.class);
        } else {
            return null;
        }
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), // Use email as the username
                user.getPassword(), // Password
                getAuthority(user) // Convert role to GrantedAuthority
        );
    }

    @Override
    public int addUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            // Map UserDTO to User entity
            User user = modelMapper.map(userDTO, User.class);

            // Encrypt the password
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            // Set default role if not provided
            if (user.getRole() == null) {
                user.setRole(UserRole.CUSTOMER); // Default role
            }

            // Set default verification status if not provided
            if (user.getVerificationStatus() == null) {
                user.setVerificationStatus("Pending"); // Example default value
            }
            userRepository.save(user);
            // Return success response
            return VarList.Created;
        }
    }


    @Override
    public ResponseDTO updateUser(UserDTO userDTO) {
        if (!userRepository.existsById(userDTO.getUserId())) {
            return new ResponseDTO(VarList.Not_Found, "User not found with id: " + userDTO.getUserId(), null);
        }

        // Map UserDTO to User entity
        User user = modelMapper.map(userDTO, User.class);

        // Encrypt the password if it's being updated
        if (userDTO.getPassword() != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        // Save the updated user
        userRepository.save(user);

        // Return success response
        return new ResponseDTO(VarList.OK, "User updated successfully", userDTO);
    }

    @Override
    public ResponseDTO deleteUser(int userId) {
        if (!userRepository.existsById(userId)) {
            return new ResponseDTO(VarList.Not_Found, "User not found with id: " + userId, null);
        }

        // Delete the user
        userRepository.deleteById(userId);

        // Return success response
        return new ResponseDTO(VarList.OK, "User deleted successfully", null);
    }

    @Override
    public ResponseDTO getAllUsers() {
        List<UserDTO> userList = modelMapper.map(userRepository.findAll(),
                new TypeToken<List<UserDTO>>() {}.getType());
        return new ResponseDTO(VarList.OK, "Users retrieved successfully", userList);
    }

    @Override
    public ResponseDTO getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new ResponseDTO(VarList.OK, "User retrieved successfully", userDTO);
    }
    

}