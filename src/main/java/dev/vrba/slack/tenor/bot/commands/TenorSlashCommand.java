package dev.vrba.slack.tenor.bot.commands;

import dev.vrba.slack.tenor.bot.service.slack.SlackService;
import dev.vrba.slack.tenor.bot.service.storage.ResultsStorageService;
import dev.vrba.slack.tenor.bot.service.storage.SearchResults;
import dev.vrba.slack.tenor.bot.service.tenor.TenorService;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@Singleton
@AllArgsConstructor
public class TenorSlashCommand {
    @NonNull
    private final TenorService tenorService;

    @NonNull
    private final SlackService slackService;

    @NonNull
    private final ResultsStorageService storageService;

    @NonNull
    private final static Logger logger = LoggerFactory.getLogger(TenorSlashCommand.class);

    @NonNull
    public Mono<Void> run(@NonNull TenorSlashCommandPayload payload) {
        logger.info("Running the /tenor command with the following query: [{}]", payload.getQuery());

        final var modal = slackService.openLoadingView(payload.getTrigger(), payload.getResponse());
        final var gifs = tenorService.search(payload.getQuery());

        return modal.zipWith(gifs)
            .flatMap(tuple -> {
                final var viewId = tuple.getT1().getId().getId();
                final var results = new SearchResults(tuple.getT2(), 0);
                final var imageUrl = results.getCurrentResult();

                storageService.store(viewId, results);

                return slackService.updateSelectionView(
                    viewId,
                    imageUrl,
                    payload.getResponse()
                );
            });
    }
}
