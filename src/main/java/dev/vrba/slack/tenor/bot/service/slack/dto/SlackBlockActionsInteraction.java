package dev.vrba.slack.tenor.bot.service.slack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@Serdeable
@AllArgsConstructor
public final class SlackBlockActionsInteraction implements SlackInteraction {
    @NonNull
    @JsonProperty("user")
    private final SlackUser user;

    @NonNull
    @JsonProperty("view")
    private final SlackView view;

    @NonNull
    @JsonProperty("actions")
    private final List<SlackAction> actions;
}
