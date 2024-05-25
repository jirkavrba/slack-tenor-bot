package dev.vrba.slack.tenor.bot.service.tenor;

import io.micronaut.context.annotation.Value;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

@Singleton
public class TenorService {
    @NonNull
    private final static Logger logger = LoggerFactory.getLogger(TenorService.class);

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
        return client.search(query, key)
            .doOnError(error -> logger.error("Error contacting tenor.com API", error))
            .map(response ->
                response.getResults()
                    .stream()
                    .map(result -> result.getFormats().getMedium().getUrl())
                    .toList()
            );
    }
}
