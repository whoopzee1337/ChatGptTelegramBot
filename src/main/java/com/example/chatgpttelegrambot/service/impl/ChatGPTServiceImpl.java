package com.example.chatgpttelegrambot.service.impl;

import com.example.chatgpttelegrambot.model.ChatGPTRequest;
import com.example.chatgpttelegrambot.model.ChatGPTResponse;
import com.example.chatgpttelegrambot.model.Message;
import com.example.chatgpttelegrambot.service.ChatGPTService;
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

    @Value("${openai.api.key}")
    private String apiKey;

    private static final String OPEN_AI_CHAT_ENDPOINT = "https://api.openai.com/v1/chat/completions";

    private final RestTemplate restTemplate;

    @Override
    public ChatGPTResponse getChatGPTResponse(String prompt) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        ChatGPTRequest chatGPTRequest = new ChatGPTRequest();
        chatGPTRequest.setModel("gpt-3.5-turbo");
        chatGPTRequest.setMessages(List.of(Message.builder().role("user").content(prompt).build()));
        chatGPTRequest.setMax_tokens(2000);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ChatGPTRequest> request = new HttpEntity<>(chatGPTRequest, headers);

        return restTemplate.postForObject(OPEN_AI_CHAT_ENDPOINT, request, ChatGPTResponse.class);
    }
}
