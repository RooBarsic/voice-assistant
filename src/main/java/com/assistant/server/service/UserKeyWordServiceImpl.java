package com.assistant.server.service;

import com.assistant.server.platform.dto.UiPlatform;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserKeyWordServiceImpl implements UserKeyWordService {

    private final Map<String, String> userByChatId = new HashMap<>();
    private final Set<String> userSet = new HashSet<>();

    UserKeyWordServiceImpl() {
    }

    @Override
    public boolean isNewUser(UiPlatform platform, String userChatId) {
        String chatIdKey = getChatIdKey(platform, userChatId);
        boolean contains = !userSet.contains(chatIdKey);
        userSet.add(chatIdKey);
        return contains;
    }

    @Override
    public boolean removeUser(UiPlatform platform, String userChatId) {
        String chatIdKey = getChatIdKey(platform, userChatId);
        userSet.remove(chatIdKey);
        userByChatId.remove(chatIdKey);
        return true;
    }

    @Override
    public boolean hasKeyWord(UiPlatform platform, String userChatId) {
        String chatIdKey = getChatIdKey(platform, userChatId);
        return userByChatId.containsKey(chatIdKey);
    }

    @Override
    public String registerUser(UiPlatform platform, String userChatId, String userName) {
        String chatIdKey = getChatIdKey(platform, userChatId);
        userByChatId.put(chatIdKey, userName);
        return userName;
    }

    @Override
    public String getUserKeyWord(UiPlatform platform, String userChatId) {
        return userByChatId.get(getChatIdKey(platform, userChatId));
    }

    private String getChatIdKey(UiPlatform platform, String userChatId) {
        return platform + userChatId;
    }
}
