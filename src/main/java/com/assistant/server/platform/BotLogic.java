package com.assistant.server.platform;


import com.assistant.server.platform.dto.BotNetMessage;
import com.assistant.server.platform.dto.UiPlatform;

public interface BotLogic {
    void setResponseSender(UiPlatform platform, BotResponseSender responseSender);

    BotNetMessage processIncomingMessage(BotNetMessage message);
}
