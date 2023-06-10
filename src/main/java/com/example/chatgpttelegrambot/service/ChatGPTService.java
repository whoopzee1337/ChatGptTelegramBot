package com.example.chatgpttelegrambot.service;

import com.example.chatgpttelegrambot.model.ChatGPTResponse;

public interface ChatGPTService {
    ChatGPTResponse getChatGPTResponse(String prompt);
}
