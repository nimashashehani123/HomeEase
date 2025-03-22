package com.example.homeease.Controller;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.ChatDTO;
import com.example.homeease.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createChat(@RequestBody ChatDTO chatDTO) {
        ResponseDTO response = chatService.createChat(chatDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllChats() {
        ResponseDTO response = chatService.getAllChats();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ResponseDTO> getChatById(@PathVariable int chatId) {
        ResponseDTO response = chatService.getChatById(chatId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping("/{chatId}")
    public ResponseEntity<ResponseDTO> updateChat(@PathVariable int chatId, @RequestBody ChatDTO chatDTO) {
        ResponseDTO response = chatService.updateChat(chatId, chatDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<ResponseDTO> deleteChat(@PathVariable int chatId) {
        ResponseDTO response = chatService.deleteChat(chatId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}