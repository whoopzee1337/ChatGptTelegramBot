package com.example.chatgpttelegrambot.bot;

import com.example.chatgpttelegrambot.service.ChatGPTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class GPTBot extends TelegramLongPollingBot {

    public GPTBot(@Value("${bot.token}") String botToken, ChatGPTService chatGPTService) {
        super(botToken);
        this.chatGPTService = chatGPTService;
    }

    private final ChatGPTService chatGPTService;

    private static final String START = "/start";

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) return;
        var message = update.getMessage().getText();
        var chatId = update.getMessage().getChatId();
        var userName = update.getMessage().getChat().getUserName();
        log.info(message + "    |||    ПОЛЬЗОВАТЕЛЬ: " + userName);
        switch (message) {
            case START -> startCommand(chatId, userName);
            default -> {
                String responseMessage = chatGPTService.getChatGPTResponse(message).getChoices().get(0).getMessage().getContent();
                sendMessage(chatId, responseMessage);
                log.info(responseMessage + "    |||    ПОЛЬЗОВАТЕЛЬ: " + userName);
            }
        }

    }

    @Override
    public String getBotUsername() {
        return "SanyaGPTBot";
    }

    private void startCommand(Long chatId, String userName) {
        var text = "Кулити, %s! Напиши сообщение для GPT.";
        var formattedText = String.format(text, userName);
        sendMessage(chatId, formattedText);
    }

    private void sendMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправления сообщения", e);
        }
    }
}
