package dev.vrba.slack.tenor.bot.service.slack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Serdeable
@AllArgsConstructor
public final class SlackImageBlock implements SlackBlock {
    @NonNull
    @JsonProperty("type")
    private final String type = "image";

    @NonNull
    @JsonProperty("image_url")
    private final String url;

    @NonNull
    @JsonProperty("alt_text")
    private final String alt;
}
