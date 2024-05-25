package dev.vrba.slack.tenor.bot.controller;

import dev.vrba.slack.tenor.bot.commands.TenorSlashCommand;
import dev.vrba.slack.tenor.bot.commands.TenorSlashCommandPayload;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Controller
@AllArgsConstructor
public class SlackCommandController {

    @NonNull
    private final TenorSlashCommand tenor;

    @Post("/tenor")
    @NonNull
    public Mono<HttpResponse<Void>> handleTenorCommand(@NonNull @Body Map<String, String> request) {
        final var payload = new TenorSlashCommandPayload(
            request.get("query"),
            request.get("trigger_id"),
            request.get("response_url")
        );

        tenor.run(payload)
            .subscribeOn(Schedulers.boundedElastic())
            .subscribe();

        return Mono.just(HttpResponse.noContent());
    }
}
