package com.assistant.server.platform;


import com.assistant.server.platform.dto.BotNetMessage;

public interface BotLogic {
    BotNetMessage processIncomingMessage(BotNetMessage message);
}
