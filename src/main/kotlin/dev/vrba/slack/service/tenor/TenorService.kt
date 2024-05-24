package dev.vrba.slack.service.tenor

import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton

@Singleton
class TenorService(
    @Value("\${credentials.tenor.api-key}")
    private val key: String,
    private val client: TenorApiClient,
) {
    suspend fun search(query: String): List<String> {
        return client.search(query, key = key).results.map { it.formats.medium.url }
    }
}
