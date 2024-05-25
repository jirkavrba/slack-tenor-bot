package dev.vrba.slack.tenor.bot.controller;

import dev.vrba.slack.tenor.bot.commands.TenorSlashCommand;
import dev.vrba.slack.tenor.bot.commands.TenorSlashCommandPayload;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@AllArgsConstructor
@Controller("/api/command")
public class SlackCommandController {

    @NonNull
    private final TenorSlashCommand tenor;

    @NonNull
    private final static Logger logger = LoggerFactory.getLogger(SlackCommandController.class);

    @NonNull
    @Post(
        value = "/tenor",
        consumes = MediaType.APPLICATION_FORM_URLENCODED,
        produces = MediaType.APPLICATION_JSON
    )
    public Mono<HttpResponse<Void>> handleTenorCommand(@NonNull @Body Map<String, String> request) {
        final var payload = new TenorSlashCommandPayload(
            request.get("text"),
            request.get("trigger_id"),
            request.get("response_url")
        );

        tenor.run(payload)
            .doOnError(error -> logger.error("Error while running the /tenor command", error))
            .subscribeOn(Schedulers.boundedElastic())
            .subscribe();

        return Mono.just(HttpResponse.noContent());
    }
}
