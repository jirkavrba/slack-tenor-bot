package dev.vrba.slack.tenor.bot.service.slack.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Serdeable
@AllArgsConstructor
public class SlackUser {
    @NonNull
    @JsonProperty("id")
    private final String id;
}
