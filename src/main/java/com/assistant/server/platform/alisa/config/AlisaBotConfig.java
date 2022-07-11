package com.assistant.server.platform.alisa.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "bots.yandex-alisa")
@ConditionalOnProperty(value = "bots.yandex-alisa.connect", havingValue = "true")
public class AlisaBotConfig {

    String stopWord = "";
    String welcomeWord = "";
    String byeWord = "";
}
