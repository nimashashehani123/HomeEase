package com.example.homeease.Controller;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.UserDTO;
import com.example.homeease.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDTO) {
        ResponseDTO response = userService.addUser(userDTO);

        // Use appropriate HTTP status codes
        HttpStatus status = response.getCode() == 200 ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO userDTO) {
        ResponseDTO response = userService.updateUser(userDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable int userId) {
        ResponseDTO response = userService.deleteUser(userId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllUsers() {
        ResponseDTO response = userService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable int userId) {
        ResponseDTO response = userService.getUserById(userId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}