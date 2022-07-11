package com.assistant.server.platform.alisa.utils;

import com.assistant.server.platform.alisa.model.request.YandexMessage;
import com.assistant.server.platform.dto.BotNetMessage;
import com.assistant.server.platform.dto.UiPlatform;

public class AlisaMessageConverter {

    public static BotNetMessage convertToBotNetMessage(YandexMessage from, BotNetMessage to) {
        if (from == null) {
            return to;
        }
        if (to == null) {
            to = new BotNetMessage();
        }

        to.setUserChatId(from.getSession().getUserId());
        to.setUiPlatform(UiPlatform.YANDEX_ALISA);
        to.setMessage(from.getRequest().getCommand());

        return to;
    }
}
