package dev.vrba.slack.tenor.bot.service.tenor;

import io.micronaut.context.annotation.Value;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

import java.util.List;

@Singleton
public class TenorService {

    @NonNull
    private final TenorApiClient client;

    @NonNull
    private final String key;

    public TenorService(
        @NonNull final TenorApiClient client,
        @NonNull @Value("${bot.credentials.tenor.api-key}") final String key
    ) {
        this.client = client;
        this.key = key;
    }

    @NonNull
    public Mono<List<String>> search(@NonNull String query) {
        return client.search(query, key).map(response ->
            response.getResults()
                .stream()
                .map(result -> result.getFormats().getMedium().getUrl())
                .toList()
        );
    }
}
