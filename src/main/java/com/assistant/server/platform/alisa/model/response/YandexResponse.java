package com.assistant.server.platform.alisa.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class YandexResponse {
    @JsonProperty("response")
    private Response response;

    @JsonProperty("version")
    private String version;

}
