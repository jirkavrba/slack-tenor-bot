package dev.vrba.slack.service.slack.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class SlackViewOpenRequest(
    @JsonProperty("trigger_id")
    val trigger: String,
    @JsonProperty("view")
    val view: SlackView,
)
