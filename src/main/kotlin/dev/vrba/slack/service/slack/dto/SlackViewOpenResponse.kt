package dev.vrba.slack.service.slack.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class SlackViewOpenResponse(
    @JsonProperty("view")
    val view: SlackViewId,
)
