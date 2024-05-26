package dev.vrba.slack.tenor.bot.service.slack.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = SlackBlockActionsInteraction.class, name = "block_actions"),
    @JsonSubTypes.Type(value = SlackViewSubmissionInteraction.class, name = "view_submission")
})
public sealed interface SlackInteraction permits SlackBlockActionsInteraction, SlackViewSubmissionInteraction {
}
