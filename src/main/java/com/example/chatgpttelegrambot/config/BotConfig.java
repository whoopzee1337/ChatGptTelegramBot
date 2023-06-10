package com.example.chatgpttelegrambot.config;

import com.example.chatgpttelegrambot.bot.GPTBot;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(GPTBot gptBot) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(gptBot);
        return api;
    }
}
