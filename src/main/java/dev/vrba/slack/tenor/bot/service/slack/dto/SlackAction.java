package dev.vrba.slack.tenor.bot.service.slack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Serdeable
@AllArgsConstructor
public class SlackAction {
    @NonNull
    @JsonProperty("action_id")
    private final String id;

    @NonNull
    @JsonProperty("block_id")
    private final String block;

    @NonNull
    @JsonProperty("text")
    private final SlackTextBlock text;

    @NonNull
    @JsonProperty("value")
    private final String value;
}
