package dev.vrba.slack.tenor.bot.service.tenor;

import dev.vrba.slack.tenor.bot.service.tenor.dto.TenorSearchResponse;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Mono;

@Client(id = "tenor")
public interface TenorApiClient {

    @Get("/v2/search")
    @NonNull
    Mono<TenorSearchResponse> search(
        @NonNull @QueryValue("q") String query,
        @NonNull @QueryValue("key") String key
    );
}
