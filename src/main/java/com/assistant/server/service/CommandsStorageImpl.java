package com.assistant.server.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class CommandsStorageImpl implements CommandsStorage {
    private final ConcurrentLinkedQueue<String> storedCommands = new ConcurrentLinkedQueue<>();

    @Override
    public void saveCommand(String command) {
        storedCommands.add(command);
    }

    @Override
    public List<String> pollAllCommands() {
        if (storedCommands.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> commands = new ArrayList<>();
        while (!storedCommands.isEmpty()) {
            String command = storedCommands.poll();
            commands.add(command);
        }
        return commands;
    }
}
