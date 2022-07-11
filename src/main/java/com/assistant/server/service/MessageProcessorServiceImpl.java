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
    private String SERVICE_NAME = "BotLogicImpl: ";
    private ConcurrentLinkedDeque<BotNetMessage> responseQueue = new ConcurrentLinkedDeque<>();
    private Map<UiPlatform, BotResponseSender> responseSenderMap = new HashMap<>();


    @Autowired
    MessageProcessorServiceImpl(final CommandsStorage commandsStorage) {
        this.commandsStorage = commandsStorage;
    }

    @Override
    public void setResponseSender(UiPlatform platform, BotResponseSender responseSender) {

    }

    @Override
    public BotNetMessage processIncomingMessage(BotNetMessage message) {
        commandsStorage.saveCommand(message.getMessage());

        BotNetMessage response = new BotNetMessage();
        response.setUiPlatform(message.getUiPlatform());
        response.setUserChatId(message.getUserChatId());
        response.setMessage("Command accepted");
        responseQueue.add(response);

        return response;
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
