package com.assistant.server.platform;

import com.assistant.server.platform.dto.BotNetMessage;
import com.assistant.server.platform.dto.BotNetMessageWithButtons;
import com.assistant.server.platform.dto.UiPlatform;
import org.jetbrains.annotations.NotNull;

public interface BotResponseSender {
    UiPlatform getPlatformName();

    boolean sendBotNetResponse(@NotNull final BotNetMessage botNetResponse);
    boolean sendBotNetResponse(@NotNull final BotNetMessageWithButtons botNetResponse);
}
