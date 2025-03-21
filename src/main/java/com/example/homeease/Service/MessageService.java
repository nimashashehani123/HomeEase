package com.example.homeease.Service;

import com.example.homeease.Entity.Message;
import java.util.List;

public interface MessageService {
    Message sendMessage(Message message);
    List<Message> getAllMessages();
    Message getMessageById(int id);
    Message updateMessage(int id, Message message);
    void deleteMessage(int id);
}