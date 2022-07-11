package com.assistant.server.platform.alisa.service;

import com.assistant.server.platform.BotRequestListener;
import com.assistant.server.platform.alisa.model.request.YandexMessage;
import com.assistant.server.platform.alisa.model.response.YandexResponse;

public interface AlisaBotRequestListener extends BotRequestListener {

    YandexResponse processAlisaMessage(YandexMessage message);
}
