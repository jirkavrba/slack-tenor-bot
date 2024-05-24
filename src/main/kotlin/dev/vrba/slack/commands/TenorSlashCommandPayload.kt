package dev.vrba.slack.commands

data class TenorSlashCommandPayload(
    val query: String,
    val trigger: String,
    val response: String,
)
