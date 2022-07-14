package com.assistant.server.service;

import com.assistant.server.platform.dto.UiPlatform;

public interface UserKeyWordService {

    boolean isNewUser(UiPlatform platform, String userChatId);
    boolean removeUser(UiPlatform platform, String userChatId);
    boolean hasKeyWord(UiPlatform platform, String userChatId);

    String registerUser(UiPlatform platform, String userChatId, String userName);

    String getUserKeyWord(UiPlatform platform, String userChatId);
}
