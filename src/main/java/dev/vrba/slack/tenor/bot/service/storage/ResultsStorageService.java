package dev.vrba.slack.tenor.bot.service.storage;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Singleton
public class ResultsStorageService {

    @NonNull
    private final Map<String, SearchResults> storage = new ConcurrentHashMap<>();

    @NonNull
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @NonNull
    public Optional<SearchResults> get(@NonNull String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @NonNull
    public SearchResults store(@NonNull String id, @NonNull SearchResults results) {
        storage.put(id, results);
        scheduler.schedule(() -> storage.remove(id), 30, TimeUnit.MINUTES);

        return results;

    }
}
