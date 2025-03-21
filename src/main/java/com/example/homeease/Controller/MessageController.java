package com.example.homeease.Controller;

import com.example.homeease.Entity.Message;
import com.example.homeease.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable int id) {
        return messageService.getMessageById(id);
    }

    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable int id, @RequestBody Message message) {
        return messageService.updateMessage(id, message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
    }
}