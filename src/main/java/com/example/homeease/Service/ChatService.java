package com.example.homeease.Service;

import com.example.homeease.Entity.Chat;
import java.util.List;

public interface ChatService {
    Chat createChat(Chat chat);
    List<Chat> getAllChats();
    Chat getChatById(int id);
    Chat updateChat(int id, Chat chat);
    void deleteChat(int id);
}