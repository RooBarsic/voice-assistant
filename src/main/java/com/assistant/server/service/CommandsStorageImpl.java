package com.assistant.server.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class CommandsStorageImpl implements CommandsStorage {
    private final Map<String, ConcurrentLinkedQueue<String>> storedCommandsByUser = new HashMap<>();

    @Override
    public void saveCommand(String userName, String command) {
        synchronized (storedCommandsByUser) {
            ConcurrentLinkedQueue<String> commands = storedCommandsByUser.getOrDefault(userName, null);
            if (commands == null) {
                storedCommandsByUser.put(userName, commands = new ConcurrentLinkedQueue<>());
            }
            commands.add(command);
        }
    }

    @Override
    public String poolFirstCommand(String userName) {
        synchronized (storedCommandsByUser) {
            if (storedCommandsByUser.containsKey(userName)) {
                ConcurrentLinkedQueue<String> commands = storedCommandsByUser.get(userName);
                return commands.isEmpty() ? "" : commands.poll();
            }
        }
        return "";
    }
}
