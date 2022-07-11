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
public class Session {
    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("skill_id")
    private String skillId;

    @JsonProperty("user")
    private User user;

    @JsonProperty("application")
    private Application application;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("new")
    private Boolean isNew;

}

/*
  "session": {
    "message_id": 0,
    "session_id": "d8fce217-33fb-48dc-91cb-cd0d33994125",
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

 */