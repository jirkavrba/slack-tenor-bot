package dev.vrba.slack.commands

import dev.vrba.slack.extension.deriveLogger
import dev.vrba.slack.service.slack.SlackModalService
import dev.vrba.slack.service.storage.Results
import dev.vrba.slack.service.storage.ResultsStorageService
import dev.vrba.slack.service.tenor.TenorService
import jakarta.inject.Singleton

@Singleton
class TenorSlashCommand(
    private val tenorService: TenorService,
    private val slackService: SlackModalService,
    private val storageService: ResultsStorageService,
) {
    private val logger = deriveLogger()

    suspend fun run(payload: TenorSlashCommandPayload) {
        logger.info("Running /tenor command with the following query: [{}]", payload.query)

        val modal = slackService.openLoadingView(payload.trigger, payload.response)
        val results = Results(gifs = tenorService.search(payload.query))

        slackService.updateSelectionView(modal.view.id, results.current, payload.response)
        storageService.store(modal.view.id, results)
    }
}
