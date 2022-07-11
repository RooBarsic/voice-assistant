package com.assistant.server.platform.telegram.service;

import com.assistant.server.platform.BotLogic;
import com.assistant.server.platform.dto.BotNetButton;
import com.assistant.server.platform.dto.BotNetMessage;
import com.assistant.server.platform.dto.BotNetMessageWithButtons;
import com.assistant.server.platform.dto.UiPlatform;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@ConditionalOnProperty(value = "bots.telegram.connect", havingValue = "true")
public class TelegramBotResponseSenderImpl extends DefaultAbsSender implements TelegramBotResponseSender {
    protected final String BOT_TOKEN;
    private final UiPlatform PLATFORM = UiPlatform.TELEGRAM;

    public TelegramBotResponseSenderImpl(@NotNull final DefaultBotOptions options, @NotNull final String BOT_TOKEN) {
        super(options);
        this.BOT_TOKEN = BOT_TOKEN;
    }

    @Autowired
    public TelegramBotResponseSenderImpl(TelegramBotRequestListener telegramBotRequestListener,
                                         BotLogic botLogic) {
        super(telegramBotRequestListener.getOptions());
        this.BOT_TOKEN = telegramBotRequestListener.getBotToken();
        botLogic.setResponseSender(UiPlatform.TELEGRAM, this);
    }

    @Override
    public UiPlatform getPlatformName() {
        return PLATFORM;
    }

    @Override
    public boolean sendBotNetResponse(@NotNull final BotNetMessage botNetMessage) {
        final String receiverChatId = botNetMessage.getUserChatId();
        final String responseText = botNetMessage.getMessage();

        // send files if has some

        final SendMessage telegramResponseMessage = new SendMessage();
        telegramResponseMessage.setChatId(receiverChatId);
        telegramResponseMessage.setText(responseText);

        // send response
        try {
            execute(telegramResponseMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean sendBotNetResponse(@NotNull BotNetMessageWithButtons botNetMessage) {
        final String receiverChatId = botNetMessage.getUserChatId();
        final String responseText = botNetMessage.getMessage();

        // send files if has some

        final SendMessage telegramResponseMessage = new SendMessage();
        telegramResponseMessage.setChatId(receiverChatId);
        telegramResponseMessage.setText(responseText);

        // add buttons if has some
        if (botNetMessage.hasButtons()) {
            if (botNetMessage.isInlineButtons()) {
                final List<List<BotNetButton>> buttonsMatrix = botNetMessage.getButtonsMatrix();
                final List<KeyboardRow> keyboardRowList = new ArrayList<>();
                for (final List<BotNetButton> buttonsInRow : buttonsMatrix) {
                    final KeyboardRow keyboardRow = new KeyboardRow();

                    for (final BotNetButton botNetButton : buttonsInRow) {
                        keyboardRow.add(botNetButton.getButtonHiddenText());
                    }
                    keyboardRowList.add(keyboardRow);
                }

                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                replyKeyboardMarkup.setKeyboard(keyboardRowList);
                replyKeyboardMarkup.setResizeKeyboard(true);
                telegramResponseMessage.setReplyMarkup(replyKeyboardMarkup);
            } else {
                final List<List<BotNetButton>> buttonsMatrix = botNetMessage.getButtonsMatrix();
                final List<List<InlineKeyboardButton>> telegramButtonsMatrix = new LinkedList<>();

                for (final List<BotNetButton> buttonsInRow : buttonsMatrix) {
                    telegramButtonsMatrix.add(new LinkedList<>());

                    for (final BotNetButton botNetButton : buttonsInRow) {

                        final InlineKeyboardButton telegramKeyboardButton = new InlineKeyboardButton();
                        telegramKeyboardButton.setText(botNetButton.getButtonText());
                        telegramKeyboardButton.setCallbackData(botNetButton.getButtonHiddenText());

                        telegramButtonsMatrix
                                .get(telegramButtonsMatrix.size() - 1)
                                .add(telegramKeyboardButton);
                    }
                }

                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//                replyKeyboardMarkup.setKeyboard(telegramButtonsMatrix);
                replyKeyboardMarkup.setResizeKeyboard(true);
                telegramResponseMessage.setReplyMarkup(replyKeyboardMarkup);
                telegramResponseMessage.setReplyMarkup(replyKeyboardMarkup);
            }
        }

        // send response
        try {
            execute(telegramResponseMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return true;
    }


    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
