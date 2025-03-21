package com.example.homeease.Controller;

import com.example.homeease.Dto.UserDTO;
import com.example.homeease.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public UserDTO loginUser(@RequestParam String email, @RequestParam String password) {
        return userService.loginUser(email, password);
    }

    @PutMapping("/update")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/verify/{userId}")
    public UserDTO verifyServiceProvider(@PathVariable int userId, @RequestParam String status) {
        return userService.verifyServiceProvider(userId, status);
    }
}