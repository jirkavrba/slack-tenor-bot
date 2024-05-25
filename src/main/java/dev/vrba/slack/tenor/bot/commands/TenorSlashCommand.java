package dev.vrba.slack.tenor.bot.commands;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@Singleton
public class TenorSlashCommand {

    @NonNull
    private final static Logger logger = LoggerFactory.getLogger(TenorSlashCommand.class);

    @NonNull
    public Mono<Void> run(@NonNull TenorSlashCommandPayload payload) {
        logger.info("Running the /tenor command with the following query: [{}]", payload.getQuery());

        return Mono.empty().then();
    }
}
