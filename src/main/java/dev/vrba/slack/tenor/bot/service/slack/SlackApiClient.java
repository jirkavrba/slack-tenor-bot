package dev.vrba.slack.tenor.bot.service.slack;

import dev.vrba.slack.tenor.bot.service.slack.dto.SlackViewOpenRequest;
import dev.vrba.slack.tenor.bot.service.slack.dto.SlackViewResponse;
import dev.vrba.slack.tenor.bot.service.slack.dto.SlackViewUpdateRequest;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Mono;

@Client(id = "slack")
@Header(name = HttpHeaders.AUTHORIZATION, value = "Bearer ${bot.credentials.slack.bot-token}")
@Header(name = HttpHeaders.CONTENT_TYPE, value = MediaType.APPLICATION_JSON)
public interface SlackApiClient {

    @NonNull
    @Post("/api/views.open")
    Mono<SlackViewResponse> openView(@NonNull @Body SlackViewOpenRequest request);

    @NonNull
    @Post("/api/views.update")
    Mono<SlackViewResponse> updateView(@NonNull @Body SlackViewUpdateRequest request);

}
