package dev.vrba.slack.service.slack.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class SlackViewUpdateRequest(
    @JsonProperty("view_id")
    val id: String,
    @JsonProperty("view")
    val view: SlackView,
)
