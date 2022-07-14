package com.assistant.server.service;

import com.assistant.server.platform.BotLogic;
import com.assistant.server.platform.BotResponseSender;
import com.assistant.server.platform.dto.BotNetMessage;
import com.assistant.server.platform.dto.UiPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class MessageProcessorServiceImpl implements BotLogic {
    private final CommandsStorage commandsStorage;
    private final UserKeyWordService userKeyWordService;
    private String SERVICE_NAME = "BotLogicImpl: ";
    private ConcurrentLinkedDeque<BotNetMessage> responseQueue = new ConcurrentLinkedDeque<>();
    private Map<UiPlatform, BotResponseSender> responseSenderMap = new HashMap<>();


    @Autowired
    MessageProcessorServiceImpl(final CommandsStorage commandsStorage,
                                final UserKeyWordService userKeyWordService) {
        this.commandsStorage = commandsStorage;
        this.userKeyWordService = userKeyWordService;
    }

    @Override
    public void setResponseSender(UiPlatform platform, BotResponseSender responseSender) {
        responseSenderMap.put(platform, responseSender);
    }

    @Override
    public BotNetMessage processIncomingMessage(BotNetMessage message) {
        System.out.println("Got message from " + message.getUiPlatform() + " chatId = " + message.getUserChatId() + " message = " + message.getMessage());

        if (userKeyWordService.isNewUser(message.getUiPlatform(), message.getUserChatId())) {
            BotNetMessage response = new BotNetMessage();
            response.setUiPlatform(message.getUiPlatform());
            response.setUserChatId(message.getUserChatId());
            response.setMessage("Hello. Please send me your userName");
            responseQueue.add(response);

            return response;
        }
        else if (!userKeyWordService.hasKeyWord(message.getUiPlatform(), message.getUserChatId())) {
            String userKeyWord = userKeyWordService.registerUser(message.getUiPlatform(), message.getUserChatId(), message.getMessage());

            BotNetMessage response = new BotNetMessage();
            response.setUiPlatform(message.getUiPlatform());
            response.setUserChatId(message.getUserChatId());
            response.setMessage("Thank you. Your userName saved as '" + userKeyWord + "'. \n" +
                    "Please send commands now.");
            responseQueue.add(response);

            return response;
        }
        else if (message.getMessage().equals("/stop")) {
            userKeyWordService.removeUser(message.getUiPlatform(), message.getUserChatId()) ;
            BotNetMessage response = new BotNetMessage();
            response.setUiPlatform(message.getUiPlatform());
            response.setUserChatId(message.getUserChatId());
            response.setMessage("Your userName was removed. Now you can set new userName.");
            responseQueue.add(response);

            return response;
        }
        else {
            String userKeyWord = userKeyWordService.getUserKeyWord(message.getUiPlatform(), message.getUserChatId());
            commandsStorage.saveCommand(userKeyWord, message.getMessage());

            BotNetMessage response = new BotNetMessage();
            response.setUiPlatform(message.getUiPlatform());
            response.setUserChatId(message.getUserChatId());
            response.setMessage("Command accepted");
            responseQueue.add(response);

            return response;
        }
    }

    @Scheduled(fixedDelay = 1000)
    void sendResponses() {
        while (!responseQueue.isEmpty()) {
            BotNetMessage response = responseQueue.pollFirst();
            BotResponseSender responseSender = responseSenderMap.getOrDefault(response.getUiPlatform(), null);

            if (responseSender != null) {
                responseSender.sendBotNetResponse(response);
            }
        }
    }
}
