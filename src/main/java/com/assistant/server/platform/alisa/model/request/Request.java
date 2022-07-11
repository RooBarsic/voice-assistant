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
public class Request {
    @JsonProperty("command")
    private String command;

    @JsonProperty("original_utterance")
    private String originalUtterance;

//    @JsonProperty("nlu")
//    private Nlu nlu;


}

/*
  "request": {
    "command": "привет",
    "original_utterance": "Привет",
    "nlu": {
      "tokens": [
        "привет"
      ],
      "entities": [],
      "intents": {}
    },
    "markup": {
      "dangerous_context": false
    },
    "type": "SimpleUtterance"
  },

 */