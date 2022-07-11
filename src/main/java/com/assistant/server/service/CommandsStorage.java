package com.assistant.server.service;

import java.util.List;

public interface CommandsStorage {
    void saveCommand(String command);
    List<String> pollAllCommands();
}
