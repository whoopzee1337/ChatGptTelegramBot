package com.example.chatgpttelegrambot.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String role;
    private String content;
}
