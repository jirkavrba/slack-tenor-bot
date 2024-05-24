package dev.vrba.slack.service.slack.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SlackSectionBlock::class, name = "section"),
    JsonSubTypes.Type(value = SlackTextBlock::class, name = "plain_text"),
    JsonSubTypes.Type(value = SlackActionsBlock::class, name = "actions"),
    JsonSubTypes.Type(value = SlackImageBlock::class, name = "image"),
)
sealed interface SlackBlock {
    val type: String
}

@Serdeable
data class SlackSectionBlock(
    @JsonProperty("type")
    override val type: String = "section",
    @JsonProperty("text")
    val text: SlackTextBlock,
) : SlackBlock

@Serdeable
data class SlackTextBlock(
    @JsonProperty("type")
    override val type: String = "plain_text",
    @JsonProperty("text")
    val text: String,
    @JsonProperty("emoji")
    val emoji: Boolean = true,
) : SlackBlock

@Serdeable
data class SlackImageBlock(
    @JsonProperty("type")
    override val type: String = "image",
    @JsonProperty("image_url")
    val url: String,
    @JsonProperty("alt_text")
    val alt: String,
) : SlackBlock

@Serdeable
data class SlackActionsBlock(
    @JsonProperty("type")
    override val type: String = "actions",
    @JsonProperty("elements")
    val elements: List<SlackElementBlock> = emptyList(),
) : SlackBlock

@Serdeable
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SlackButtonBlock::class, name = "button"),
)
sealed interface SlackElementBlock {
    val type: String
}

@Serdeable
data class SlackButtonBlock(
    @JsonProperty("type")
    override val type: String = "button",
    @JsonProperty("text")
    val text: SlackTextBlock,
    @JsonProperty("value")
    val value: String,
    @JsonProperty("action_id")
    val action: String,
) : SlackElementBlock
