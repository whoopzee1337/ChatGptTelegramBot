package com.example.chatgpttelegrambot.controller;

import com.example.chatgpttelegrambot.model.ChatBotInputRequest;
import com.example.chatgpttelegrambot.model.ChatGPTRequest;
import com.example.chatgpttelegrambot.model.ChatGptResponse;
import com.example.chatgpttelegrambot.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatGptController {

    private ChatGPTService chatGPTService;

    @PostMapping("/chat")
    public ResponseEntity<ChatGptResponse> processInputRequest(@RequestBody ChatBotInputRequest chatBotInputRequest){
        ChatGptResponse chatGptResponse = chatGPTService.getChatGPTResponse(chatBotInputRequest.getMessage());
        return new ResponseEntity<>(chatGptResponse, HttpStatus.OK);
    }
}
