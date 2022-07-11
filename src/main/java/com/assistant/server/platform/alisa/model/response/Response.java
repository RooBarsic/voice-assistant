package com.assistant.server.platform.alisa.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @JsonProperty("text")
    private String text;

    @JsonProperty("end_session")
    private Boolean endSession;

}
