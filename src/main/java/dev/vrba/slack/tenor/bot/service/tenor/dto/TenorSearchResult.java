package dev.vrba.slack.tenor.bot.service.tenor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;
import lombok.Getter;

@Data
@Serdeable
public class TenorSearchResult {
    @NonNull
    @JsonProperty("media_formats")
    private final TenorSearchResultFormats formats;

    @Data
    @Serdeable
    public static class TenorSearchResultFormats {
        @NonNull
        @JsonProperty("mediumgif")
        private final TenorSearchResultUrl medium;
    }

    @Data
    @Serdeable
    public static class TenorSearchResultUrl {
        @NonNull
        @JsonProperty("url")
        private final String url;
    }
}
