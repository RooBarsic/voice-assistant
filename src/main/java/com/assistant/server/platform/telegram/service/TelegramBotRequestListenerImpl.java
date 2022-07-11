package com.assistant.server.platform.telegram.service;

import com.assistant.server.platform.BotLogic;
import com.assistant.server.platform.BotResponseSender;
import com.assistant.server.platform.dto.BotNetMessage;
import com.assistant.server.platform.dto.UiPlatform;
import com.assistant.server.platform.telegram.config.TelegramBotConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;



@Service
@ConditionalOnProperty(value = "bots.telegram.connect", havingValue = "true")
public class TelegramBotRequestListenerImpl extends TelegramLongPollingBot implements TelegramBotRequestListener {
    private final int RECONNECT_PAUSE = 10000;
    private final BotLogic botLogic;

    @Setter
    @Getter
    private String botName;

    @Setter
    private String botToken;

    @Autowired
    public TelegramBotRequestListenerImpl(TelegramBotConfig telegramBotConfig,
                                          BotLogic botLogic) {
        this.botName = telegramBotConfig.getBotName();
        this.botToken = telegramBotConfig.getBotToken();
        this.botLogic = botLogic;

        System.out.println("##### Starting Telegram bot ....... ");
        botConnect();
        System.out.println("##### Telegram bot - started ....... ");

    }

    @Override
    public void onUpdateReceived(Update update) {
        final BotNetMessage botNetRequest = new BotNetMessage();
        botNetRequest.setUiPlatform(UiPlatform.TELEGRAM);

        if (update.hasMessage()) {
            final Message telegramMessage = update.getMessage();
            botNetRequest.setUserChatId(Long.toString(telegramMessage.getChatId()));
            botNetRequest.setMessage(telegramMessage.getText());
        } else if (update.hasCallbackQuery()) {
            final CallbackQuery callbackQuery = update.getCallbackQuery();
            botNetRequest.setUserChatId(Long.toString(callbackQuery.getMessage().getChatId()));
            botNetRequest.setMessage(callbackQuery.getData());
        } else {
            return;
        }

        // add attachments if has some
        if (update.hasMessage()) {
            final Message telegramMessage = update.getMessage();
        }

        // add filled box to the processing queue
        botLogic.processIncomingMessage(botNetRequest);
    }

    @Override
    public String getBotUsername() {
        //log.debug("Bot name: " + botName);
        System.out.println(" ### Request for Bot name");
        return botName;
    }

    @Override
    public String getBotToken() {
        //log.debug("Bot token: " + botToken);
        System.out.println(" ### Request for token");
        return botToken;
    }

    private void botConnect() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
//            telegramBotsApi.registerBot(this);
            System.out.println(" ### Bot connecting....");
            telegramBotsApi.registerBot(this);
            //log.info("TelegramAPI started. Bot connected and waiting for messages");
        } catch (TelegramApiRequestException e) {
            //log.error("Cant Connect. Pause " + RECONNECT_PAUSE / 1000 + "sec and try again. Error: " + e.getMessage());
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private BotResponseSender getBotResponseSender() {
        return new TelegramBotResponseSenderImpl(getOptions(), botToken);
    }
}
