package com.example.chatgpttelegrambot.service;

import com.example.chatgpttelegrambot.model.ChatGPTRequest;
import com.example.chatgpttelegrambot.model.ChatGptResponse;

public interface ChatGPTService {
    ChatGptResponse getChatGPTResponse(String prompt);
}
