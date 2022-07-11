package com.assistant.server.controller;

import com.assistant.server.service.CommandsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public List<String> poolAllCommands() {
        return commandsStorage.pollAllCommands();
    }

    @RequestMapping(value = "/pool-first-commands", method = RequestMethod.GET)
    @ResponseBody
    public String poolFirstCommand() {
        return commandsStorage.poolFirstCommand();
    }

}
