package com.example.chatgpttelegrambot.model;

import lombok.Data;

import java.util.List;

@Data
public class ChatGPTRequest {
    private String model;
    private List<Message> messages;
    private Integer maxTokens;
}
