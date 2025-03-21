package com.example.homeease.Service;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Entity.Message;

import java.util.List;

public interface MessageService {
    Message sendMessage(Message message);
    List<Message> getAllMessages();
    Message getMessageById(int id) throws ResourceNotFoundException;
    Message updateMessage(int id, Message message) throws ResourceNotFoundException;
    void deleteMessage(int id) throws ResourceNotFoundException;
}