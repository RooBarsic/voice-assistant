package com.assistant.server.platform.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BotNetButton {
    private String buttonText = "";
    private String buttonHiddenText = "";

}
