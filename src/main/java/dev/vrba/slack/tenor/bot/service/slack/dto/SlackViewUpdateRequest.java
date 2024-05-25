package dev.vrba.slack.tenor.bot.service.slack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Serdeable
@AllArgsConstructor
public class SlackViewUpdateRequest {
    @NonNull
    @JsonProperty("view_id")
    private final String id;

    @NonNull
    @JsonProperty("view")
    private final SlackView view;
}
