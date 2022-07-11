package com.assistant.server.platform.telegram.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "bots.telegram")
@ConditionalOnProperty(value = "bots.telegram.connect", havingValue = "true")
public class TelegramBotConfig {

    String botName = "";
    String botToken = "";
}
