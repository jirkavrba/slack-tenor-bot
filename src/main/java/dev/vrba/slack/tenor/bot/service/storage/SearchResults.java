package dev.vrba.slack.tenor.bot.service.storage;

import io.micronaut.core.annotation.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;

import java.util.List;

@With
@Getter
@AllArgsConstructor
public class SearchResults {
    @NonNull
    private final List<String> results;

    @NonNull
    private final int index;

    @NonNull
    public String getCurrentResult() {
        return results.get(index % results.size());
    }
}
