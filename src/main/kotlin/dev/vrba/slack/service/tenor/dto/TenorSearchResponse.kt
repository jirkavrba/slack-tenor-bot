package dev.vrba.slack.service.tenor.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class TenorSearchResponse(
    @JsonProperty("results")
    val results: List<TenorSearchResult>,
)
