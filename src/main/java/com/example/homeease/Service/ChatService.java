package com.example.homeease.Service;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Entity.Chat;

import java.util.List;

public interface ChatService {
    Chat createChat(Chat chat);
    List<Chat> getAllChats();
    Chat getChatById(int id) throws ResourceNotFoundException;
    Chat updateChat(int id, Chat chat) throws ResourceNotFoundException;
    void deleteChat(int id) throws ResourceNotFoundException;
}