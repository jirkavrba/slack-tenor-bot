package dev.vrba.slack.service.tenor.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class TenorSearchResult(
    @JsonProperty("media_formats")
    val formats: TenorSearchResultFormats,
)

@Serdeable
data class TenorSearchResultFormats(
    @JsonProperty("mediumgif")
    val medium: TenorSearchResultUrl,
)

@Serdeable
data class TenorSearchResultUrl(
    @JsonProperty("url")
    val url: String,
)
