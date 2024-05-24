package dev.vrba.slack.controller

import dev.vrba.slack.commands.TenorSlashCommand
import dev.vrba.slack.commands.TenorSlashCommandPayload
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Controller("/api/command")
class SlackCommandController(
    private val tenor: TenorSlashCommand,
) {
    @Post(
        value = "/tenor",
        consumes = [MediaType.APPLICATION_FORM_URLENCODED],
        produces = [MediaType.APPLICATION_JSON],
    )
    suspend fun handleTenorCommand(
        @Body body: Map<String, String>,
    ): HttpResponse<String> {
        val payload =
            TenorSlashCommandPayload(
                query = body["text"].orEmpty(),
                trigger = body["trigger_id"].orEmpty(),
                response = body["response_url"].orEmpty(),
            )

        coroutineScope {
            launch(Dispatchers.IO) {
                tenor.run(payload)
            }
        }

        return HttpResponse.noContent()
    }
}
