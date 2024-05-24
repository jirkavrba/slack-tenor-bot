package dev.vrba.slack.service.slack

import dev.vrba.slack.service.slack.dto.SlackTextBlock
import dev.vrba.slack.service.slack.dto.SlackView
import dev.vrba.slack.service.slack.dto.SlackViewOpenRequest
import dev.vrba.slack.service.slack.dto.SlackViewOpenResponse
import jakarta.inject.Singleton

@Singleton
class SlackModalService(
    private val client: SlackApiClient,
) {
    suspend fun openLoadingView(
        trigger: String,
        response: String,
    ): SlackViewOpenResponse {
        val request =
            SlackViewOpenRequest(
                trigger = trigger,
                view =
                    SlackView(
                        type = "modal",
                        callback = "tenor-gif-loading",
                        title = SlackTextBlock(text = "Loading GIFs..."),
//                        blocks = listOf(SlackTextBlock(text = "This view will be updated shortly")),
                        metadata = response,
                    ),
            )

        return client.openView(request)
    }
}
