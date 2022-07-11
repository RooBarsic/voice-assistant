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
public class User {
    @JsonProperty("user_id")
    private String userId;

}

/*
    "user": {
      "user_id": "20D7D9C72C245DAE4D516A054F8C6E6F17ABC2BE88DFA964C372342124751E8E"
    },

 */