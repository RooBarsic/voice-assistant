package com.assistant.server.platform.alisa.service;

import com.assistant.server.platform.BotLogic;
import com.assistant.server.platform.alisa.config.AlisaBotConfig;
import com.assistant.server.platform.alisa.model.request.YandexMessage;
import com.assistant.server.platform.alisa.model.response.Response;
import com.assistant.server.platform.alisa.model.response.YandexResponse;
import com.assistant.server.platform.alisa.utils.AlisaMessageConverter;
import com.assistant.server.platform.dto.BotNetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "bots.yandex-alisa.connect", havingValue = "true")
public class AlisaBotRequestListenerImpl implements AlisaBotRequestListener {
    private String STOP_WORD;
    private String WELCOME_WORD;
    private String BYE_WORD;
    private final BotLogic botLogic;

    @Autowired
    AlisaBotRequestListenerImpl(AlisaBotConfig alisaBotConfig,
                                BotLogic botLogic) {
        STOP_WORD = alisaBotConfig.getStopWord();
        WELCOME_WORD = alisaBotConfig.getWelcomeWord();
        BYE_WORD = alisaBotConfig.getByeWord();
        this.botLogic = botLogic;
    }

    @Override
    public YandexResponse processAlisaMessage(YandexMessage message) {

        if (message.getSession().getIsNew()) {
            Response response = new Response(WELCOME_WORD, false);
            return new YandexResponse(response, message.getVersion());
        }
        else {
            if (message.getRequest().getCommand().contains(STOP_WORD)) {
                Response response = new Response(BYE_WORD, true);
                return new YandexResponse(response, message.getVersion());
            }
            BotNetMessage botNetMessage = AlisaMessageConverter.convertToBotNetMessage(message, null);

            BotNetMessage response = botLogic.processIncomingMessage(botNetMessage);

            Response alisaResponse = new Response(response.getMessage(), false);
            return new YandexResponse(alisaResponse, message.getVersion());
        }
    }
}
