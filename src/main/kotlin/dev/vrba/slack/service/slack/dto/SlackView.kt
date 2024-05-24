package dev.vrba.slack.service.slack.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class SlackView(
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val id: String? = null,
    @JsonProperty("type")
    val type: String,
    @JsonProperty("callback_id")
    val callback: String,
    @JsonProperty("title")
    val title: SlackTextBlock,
    @JsonProperty("blocks")
    val blocks: List<SlackBlock> = emptyList(),
    @JsonProperty("private_metadata")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val metadata: String? = null,
    @JsonProperty("submit")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val submit: SlackTextBlock? = null,
)
