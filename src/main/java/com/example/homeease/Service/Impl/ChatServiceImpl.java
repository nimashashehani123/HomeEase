package com.example.homeease.Service.Impl;

import com.example.homeease.Entity.Chat;
import com.example.homeease.Repo.ChatRepository;
import com.example.homeease.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    @Override
    public Chat getChatById(int id) {
        return chatRepository.findById(id).orElse(null);
    }

    @Override
    public Chat updateChat(int id, Chat chat) {
        Chat existingChat = chatRepository.findById(id).orElse(null);
        if (existingChat != null) {
            existingChat.setUser1(chat.getUser1());
            existingChat.setUser2(chat.getUser2());
            return chatRepository.save(existingChat);
        }
        return null;
    }

    @Override
    public void deleteChat(int id) {
        chatRepository.deleteById(id);
    }
}