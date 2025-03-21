package com.example.homeease.Service.Impl;

import com.example.homeease.Entity.Message;
import com.example.homeease.Repo.MessageRepository;
import com.example.homeease.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessageById(int id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public Message updateMessage(int id, Message message) {
        Message existingMessage = messageRepository.findById(id).orElse(null);
        if (existingMessage != null) {
            existingMessage.setContent(message.getContent());
            return messageRepository.save(existingMessage);
        }
        return null;
    }

    @Override
    public void deleteMessage(int id) {
        messageRepository.deleteById(id);
    }
}