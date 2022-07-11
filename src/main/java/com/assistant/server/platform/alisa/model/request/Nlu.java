package com.assistant.server.platform.alisa.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nlu {
    @JsonProperty("tokens")
    private String tokens;

    @JsonProperty("entities")
    private String entities;

    @JsonProperty("intents")
    private String intents;
}
