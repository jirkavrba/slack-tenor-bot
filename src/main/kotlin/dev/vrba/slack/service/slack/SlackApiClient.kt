package dev.vrba.slack.service.slack

import dev.vrba.slack.service.slack.dto.SlackViewOpenRequest
import dev.vrba.slack.service.slack.dto.SlackViewResponse
import dev.vrba.slack.service.slack.dto.SlackViewUpdateRequest
import io.micronaut.http.HttpHeaders
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client(id = "slack")
@Header(name = HttpHeaders.AUTHORIZATION, value = "Bearer \${credentials.slack.bot}")
@Header(name = HttpHeaders.CONTENT_TYPE, value = MediaType.APPLICATION_JSON)
interface SlackApiClient {
    @Post("/api/views.open")
    suspend fun openView(
        @Body request: SlackViewOpenRequest,
    ): SlackViewResponse

    @Post("/api/views.update")
    suspend fun updateView(
        @Body request: SlackViewUpdateRequest,
    ): SlackViewResponse
}
