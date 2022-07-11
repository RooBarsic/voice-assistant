package com.assistant.server.platform.telegram.service;

import com.assistant.server.platform.BotRequestListener;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public interface TelegramBotRequestListener extends BotRequestListener {

    String getBotToken();
    DefaultBotOptions getOptions();
}
