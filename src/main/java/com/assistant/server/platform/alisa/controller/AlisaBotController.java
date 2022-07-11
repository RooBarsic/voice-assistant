package com.assistant.server.platform.alisa.controller;

import com.assistant.server.platform.alisa.model.request.YandexMessage;
import com.assistant.server.platform.alisa.model.response.YandexResponse;
import com.assistant.server.platform.alisa.service.AlisaBotRequestListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/alisa")
@ConditionalOnProperty(value = "bots.yandex-alisa.connect", havingValue = "true")
public class AlisaBotController {
    private AlisaBotRequestListener alisaBotRequestListener;

    @Autowired
    AlisaBotController(AlisaBotRequestListener alisaBotRequestListener) {
        this.alisaBotRequestListener = alisaBotRequestListener;
    }

    @RequestMapping(value = "/user-message", method = RequestMethod.POST)
    public YandexResponse getPOSTReceivedMessages(@RequestBody YandexMessage message) {

        return alisaBotRequestListener.processAlisaMessage(message);
    }
}
