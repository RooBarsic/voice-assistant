package com.assistant.server.service;

import java.util.List;

public interface CommandsStorage {
    void saveCommand(String userName, String command);

    String poolFirstCommand(String userName);
}
