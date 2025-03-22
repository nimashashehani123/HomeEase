package com.example.homeease.Service;

import com.example.homeease.Entity.Message;
import com.example.homeease.Dto.MessageDTO;
import com.example.homeease.Dto.ResponseDTO;

import java.util.List;

public interface MessageService {
    ResponseDTO sendMessage(MessageDTO messageDTO); // Send a new message
    ResponseDTO getAllMessages(); // Retrieve all messages
    ResponseDTO getMessageById(int id); // Retrieve a message by ID
    ResponseDTO updateMessage(int id, MessageDTO messageDTO); // Update a message
    ResponseDTO deleteMessage(int id); // Delete a message
}