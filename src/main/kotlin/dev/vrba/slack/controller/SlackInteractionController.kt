package dev.vrba.slack.controller

import dev.vrba.slack.extension.deriveLogger
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/api/interaction")
class SlackInteractionController {
    private val logger = deriveLogger()

    @Post(
        consumes = [MediaType.APPLICATION_FORM_URLENCODED],
        produces = [MediaType.APPLICATION_JSON],
    )
    suspend fun handleInteraction(
        @Body body: Map<String, String>,
    ): HttpResponse<String> {
        logger.info(body.toString())
        return HttpResponse.noContent()
    }
}
