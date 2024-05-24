package dev.vrba.slack.service.slack

import dev.vrba.slack.service.slack.dto.SlackActionsBlock
import dev.vrba.slack.service.slack.dto.SlackButtonBlock
import dev.vrba.slack.service.slack.dto.SlackImageBlock
import dev.vrba.slack.service.slack.dto.SlackTextBlock
import dev.vrba.slack.service.slack.dto.SlackView
import dev.vrba.slack.service.slack.dto.SlackViewOpenRequest
import dev.vrba.slack.service.slack.dto.SlackViewResponse
import dev.vrba.slack.service.slack.dto.SlackViewUpdateRequest
import jakarta.inject.Singleton

@Singleton
class SlackModalService(
    private val client: SlackApiClient,
) {
    suspend fun openLoadingView(
        trigger: String,
        response: String,
    ): SlackViewResponse {
        val request =
            SlackViewOpenRequest(
                trigger = trigger,
                view =
                    SlackView(
                        type = "modal",
                        callback = "tenor-gif-loading",
                        title = SlackTextBlock(text = "Loading GIFs..."),
                        metadata = response,
                    ),
            )

        return client.openView(request)
    }

    suspend fun updateSelectionView(
        viewId: String,
        imageUrl: String,
        responseUrl: String,
    ) {
        val request =
            SlackViewUpdateRequest(
                id = viewId,
                view =
                    SlackView(
                        type = "modal",
                        callback = "tenor-gif-selection",
                        title = SlackTextBlock(text = "Tenor GIF search"),
                        submit = SlackTextBlock(text = "Send this GIF to Slack"),
                        blocks =
                            listOf(
                                SlackActionsBlock(
                                    elements =
                                        listOf(
                                            SlackButtonBlock(
                                                text = SlackTextBlock(text = "Previous"),
                                                value = "previous",
                                                action = "previous",
                                            ),
                                            SlackButtonBlock(
                                                text = SlackTextBlock(text = "Next"),
                                                value = "next",
                                                action = "next",
                                            ),
                                        ),
                                ),
                                SlackImageBlock(
                                    url = imageUrl,
                                    alt = "GIF",
                                ),
                            ),
                        metadata = responseUrl,
                    ),
            )

        client.updateView(request)
    }
}
