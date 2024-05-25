package dev.vrba.slack.tenor.bot.service.slack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Serdeable
@AllArgsConstructor
public class SlackViewOpenRequest {
    @NonNull
    @JsonProperty("trigger_id")
    private final String trigger;

    @NonNull
    @JsonProperty("view")
    private final SlackView view;
}
