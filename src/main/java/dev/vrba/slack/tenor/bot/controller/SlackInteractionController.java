package dev.vrba.slack.tenor.bot.controller;

import dev.vrba.slack.tenor.bot.service.slack.SlackService;
import dev.vrba.slack.tenor.bot.service.slack.dto.SlackBlockActionsInteraction;
import dev.vrba.slack.tenor.bot.service.slack.dto.SlackInteraction;
import dev.vrba.slack.tenor.bot.service.slack.dto.SlackViewSubmissionInteraction;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.serde.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@AllArgsConstructor
@Controller("/api/interactions")
public class SlackInteractionController {
    @NonNull
    private final ObjectMapper mapper;

    @NonNull
    private final SlackService slackService;

    @NonNull
    @SneakyThrows
    @Post(
        consumes = MediaType.APPLICATION_FORM_URLENCODED,
        produces = MediaType.APPLICATION_JSON
    )
    public Mono<HttpResponse<Void>> handle(@NonNull @Body Map<String, String> body) {
        final var payload = mapper.readValue(body.get("payload"), SlackInteraction.class);
        final var result = switch (payload) {
            case SlackViewSubmissionInteraction interaction -> slackService.handleViewSubmission(interaction);
            case SlackBlockActionsInteraction interaction -> slackService.handleBlockAction(interaction);
        };

        return result
            .subscribeOn(Schedulers.boundedElastic())
            .then(Mono.fromCallable(HttpResponse::noContent));
    }
}
