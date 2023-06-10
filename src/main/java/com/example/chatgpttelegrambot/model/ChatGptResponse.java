package com.example.chatgpttelegrambot.model;

import lombok.Data;

import java.util.List;

@Data
public class ChatGptResponse {
    private String id;
    private String object;
    private int created;
    private List<Choice> choices;
    private Usage usage;
}
