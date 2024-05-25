package dev.vrba.slack.tenor.bot.commands;

import io.micronaut.core.annotation.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TenorSlashCommandPayload {
    @NonNull
    private final String query;

    @NonNull
    private final String trigger;

    @NonNull
    private final String response;
}
