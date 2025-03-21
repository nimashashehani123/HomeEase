package com.example.homeease.Controller;

import com.example.homeease.Entity.Chat;
import com.example.homeease.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public Chat createChat(@RequestBody Chat chat) {
        return chatService.createChat(chat);
    }

    @GetMapping
    public List<Chat> getAllChats() {
        return chatService.getAllChats();
    }

    @GetMapping("/{id}")
    public Chat getChatById(@PathVariable int id) {
        return chatService.getChatById(id);
    }

    @PutMapping("/{id}")
    public Chat updateChat(@PathVariable int id, @RequestBody Chat chat) {
        return chatService.updateChat(id, chat);
    }

    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable int id) {
        chatService.deleteChat(id);
    }
}