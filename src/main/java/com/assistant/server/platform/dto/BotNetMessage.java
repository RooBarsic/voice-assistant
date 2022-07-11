package com.assistant.server.platform.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BotNetMessage {

    @JsonProperty("userChatId")
    private String userChatId;

    @JsonProperty("message")
    private String message;

    @JsonProperty("uiPlatform")
    private UiPlatform uiPlatform;

}
