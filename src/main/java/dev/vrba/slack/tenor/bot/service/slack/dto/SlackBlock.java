package dev.vrba.slack.tenor.bot.service.slack.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = SlackActionsBlock.class, name = "actions"),
    @JsonSubTypes.Type(value = SlackTextBlock.class, name = "plain_text"),
    @JsonSubTypes.Type(value = SlackImageBlock.class, name = "image")
})
public sealed interface SlackBlock permits SlackActionsBlock, SlackImageBlock, SlackTextBlock {
}
