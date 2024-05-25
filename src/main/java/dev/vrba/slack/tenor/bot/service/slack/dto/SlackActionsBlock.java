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
public final class SlackActionsBlock implements SlackBlock {
    @NonNull
    @JsonProperty("type")
    private final String type = "actions";

    @NonNull
    @JsonProperty("elements")
    private final List<SlackElement> elements;
}
