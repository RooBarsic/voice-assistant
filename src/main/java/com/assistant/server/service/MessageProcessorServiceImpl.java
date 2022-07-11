package com.assistant.server.service;

import com.assistant.server.platform.BotLogic;
import com.assistant.server.platform.dto.BotNetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessorServiceImpl implements BotLogic {
    private final CommandsStorage commandsStorage;

    @Autowired
    MessageProcessorServiceImpl(final CommandsStorage commandsStorage) {
        this.commandsStorage = commandsStorage;
    }

    @Override
    public BotNetMessage processIncomingMessage(BotNetMessage message) {
        commandsStorage.saveCommand(message.getMessage());

        BotNetMessage response = new BotNetMessage();
        response.setUiPlatform(message.getUiPlatform());
        response.setUserChatId(message.getUserChatId());
        response.setMessage("Command accepted");
        return response;
    }
}
