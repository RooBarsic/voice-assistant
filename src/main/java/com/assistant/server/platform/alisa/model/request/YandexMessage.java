package com.assistant.server.platform.alisa.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class YandexMessage {

//    @JsonProperty("meta")
//    private Meta meta;

    @JsonProperty("session")
    private Session session;

    @JsonProperty("request")
    private Request request;

    @JsonProperty("version")
    private String version;
}

/*
-------------------------------------------------
{
  "meta": {
    "locale": "ru-RU",
    "timezone": "UTC",
    "client_id": "ru.yandex.searchplugin/7.16 (none none; android 4.4.2)",
    "interfaces": {
      "screen": {},
      "payments": {},
      "account_linking": {}
    }
  },
  "session": {
    "message_id": 0,
    "session_id": "3fdd4453-5ec0-4f1e-b4d4-0c9e4ea4b410",
    "skill_id": "53723d75-35f9-4123-a03f-99d1a47a5a41",
    "user": {
      "user_id": "20D7D9C72C245DAE4D516A054F8C6E6F17ABC2BE88DFA964C372342124751E8E"
    },
    "application": {
      "application_id": "ED702EC1F76C5BE9F5AD17B4CE3DE567FA843E9CFB038160BB3512CA1013E5D1"
    },
    "user_id": "ED702EC1F76C5BE9F5AD17B4CE3DE567FA843E9CFB038160BB3512CA1013E5D1",
    "new": true
  },
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
  "version": "1.0"
}
 */