package dev.vrba.slack.commands

import dev.vrba.slack.extension.deriveLogger
import dev.vrba.slack.service.slack.SlackModalService
import dev.vrba.slack.service.tenor.TenorService
import jakarta.inject.Singleton

@Singleton
class TenorSlashCommand(
    private val tenorService: TenorService,
    private val slackService: SlackModalService,
) {
    private val logger = deriveLogger()

    suspend fun run(payload: TenorSlashCommandPayload) {
        logger.info("Running /tenor command with the following query: [{}]", payload.query)

        val view = slackService.openLoadingView(payload.trigger, payload.response)
        val results = tenorService.search(payload.query)

        println(results)
    }
}
