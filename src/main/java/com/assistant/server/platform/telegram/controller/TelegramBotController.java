package com.assistant.server.platform.telegram.controller;


import com.assistant.server.platform.dto.BotNetMessage;
import com.assistant.server.platform.telegram.service.TelegramBotResponseSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/telegram")
@ConditionalOnProperty(value = "bots.telegram.connect", havingValue = "true")
public class TelegramBotController {
    private TelegramBotResponseSender telegramResponseSender;

    @Autowired
    TelegramBotController(TelegramBotResponseSender telegramResponseSender) {
        this.telegramResponseSender = telegramResponseSender;
    }

    @RequestMapping(value = "/send-message", method = RequestMethod.POST)
    public Boolean sendMessageToTelegramBot(@RequestBody BotNetMessage message) {
        return telegramResponseSender.sendBotNetResponse(message);
    }
}
