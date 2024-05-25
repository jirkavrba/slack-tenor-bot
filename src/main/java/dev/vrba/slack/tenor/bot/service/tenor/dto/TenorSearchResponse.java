package dev.vrba.slack.tenor.bot.service.tenor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.util.List;

@Data
@Serdeable
public class TenorSearchResponse {

    @NonNull
    @JsonProperty("results")
    private final List<TenorSearchResult> results;

}
