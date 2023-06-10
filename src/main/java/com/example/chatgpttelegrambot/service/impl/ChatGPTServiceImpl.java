package com.example.chatgpttelegrambot.service.impl;

import com.example.chatgpttelegrambot.model.ChatGptResponse;
import com.example.chatgpttelegrambot.service.ChatGPTService;
import com.example.chatgpttelegrambot.model.ChatGPTRequest;
import com.example.chatgpttelegrambot.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ChatGPTServiceImpl implements ChatGPTService {

    @Value("$.openai.api.key")
    private String apiKey;

    private static final String OPEN_AI_CHAT_ENDPOINT = "https://api.openai.com/v1/chat/completions";

    private RestTemplate restTemplate;

    @Override
    public ChatGptResponse getChatGPTResponse(String prompt) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "chatgpt " + apiKey);

        ChatGPTRequest chatGPTRequest = new ChatGPTRequest();
        chatGPTRequest.setModel("gpt-3.5-turbo");
        chatGPTRequest.setMessages(List.of(Message.builder().role("user").content(prompt).build()));
        chatGPTRequest.setMaxTokens(20);

        HttpEntity<ChatGPTRequest> request = new HttpEntity<>(chatGPTRequest, httpHeaders);

        return restTemplate.postForObject(OPEN_AI_CHAT_ENDPOINT, request, ChatGptResponse.class);
    }
}
