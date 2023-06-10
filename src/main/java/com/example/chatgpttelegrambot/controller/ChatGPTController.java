package com.example.chatgpttelegrambot.controller;

import com.example.chatgpttelegrambot.model.ChatBotInputRequest;
import com.example.chatgpttelegrambot.model.ChatGPTResponse;
import com.example.chatgpttelegrambot.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @PostMapping("/chat")
    public ResponseEntity<ChatGPTResponse> processInputRequest(@RequestBody ChatBotInputRequest chatBotInputRequest) {
        return ResponseEntity.ok(chatGPTService.getChatGPTResponse(chatBotInputRequest.getMessage()));
    }
}
