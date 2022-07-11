package com.assistant.server.controller;

import com.assistant.server.service.CommandsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/commands")
public class ClientCommandsController {
    private final CommandsStorage commandsStorage;

    @Autowired
    ClientCommandsController(CommandsStorage commandsStorage) {
        this.commandsStorage = commandsStorage;
    }

    @RequestMapping(value = "/pool-all-commands", method = RequestMethod.GET)
    public List<String> sendMessageToTelegramBot() {
        return commandsStorage.pollAllCommands();
    }

}
