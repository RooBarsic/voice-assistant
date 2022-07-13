package com.assistant.server.controller;

import com.assistant.server.service.CommandsStorage;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/pool-first-commands/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public String poolFirstCommand(@PathVariable("userName") String userName) {
        return commandsStorage.poolFirstCommand(userName);
    }

}
