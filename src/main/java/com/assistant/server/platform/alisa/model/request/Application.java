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
public class Application {

    @JsonProperty("application_id")
    private String applicationId;
}

/*
    "application": {
      "application_id": "ED702EC1F76C5BE9F5AD17B4CE3DE567FA843E9CFB038160BB3512CA1013E5D1"
    },

 */