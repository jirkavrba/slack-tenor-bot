package dev.vrba.slack.tenor.bot.service.slack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Serdeable
@AllArgsConstructor
@RequiredArgsConstructor
public class SlackView {
    @Nullable
    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @NonNull
    @JsonProperty("type")
    private final String type;

    @NonNull
    @JsonProperty("callback_id")
    private final String callback;

    @NonNull
    @JsonProperty("title")
    private final SlackTextBlock title;

    @NonNull
    @JsonProperty("blocks")
    private final List<SlackBlock> blocks;

    @Nullable
    @JsonProperty("private_metadata")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String metadata;

    @Nullable
    @JsonProperty("submit")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SlackTextBlock submit;
}

