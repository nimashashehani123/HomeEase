package com.example.homeease.Service;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.ChatDTO;

public interface ChatService {
    ResponseDTO createChat(ChatDTO chatDTO);
    ResponseDTO getAllChats();
    ResponseDTO getChatById(int chatId);
    ResponseDTO updateChat(int chatId, ChatDTO chatDTO);
    ResponseDTO deleteChat(int chatId);
}