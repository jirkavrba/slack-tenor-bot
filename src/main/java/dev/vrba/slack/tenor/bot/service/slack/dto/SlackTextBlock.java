package dev.vrba.slack.tenor.bot.service.slack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Serdeable
@AllArgsConstructor
public final class SlackTextBlock implements SlackBlock {
    @NonNull
    @JsonProperty("type")
    private final String type = "plain_text";

    @NonNull
    @JsonProperty("text")
    private final String text;

    @NonNull
    @JsonProperty("emoji")
    private final boolean emoji;
}
