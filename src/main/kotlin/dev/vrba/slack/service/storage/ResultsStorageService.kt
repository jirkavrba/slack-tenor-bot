package dev.vrba.slack.service.storage

import jakarta.inject.Singleton
import java.util.concurrent.ConcurrentHashMap

@Singleton
class ResultsStorageService {
    // TODO: Store this in Redis?
    private val storage: MutableMap<String, Results> = ConcurrentHashMap()

    suspend fun get(view: String): Results? = storage[view]

    suspend fun store(
        view: String,
        results: Results,
    ): Results {
        storage[view] = results
        return results
    }
}
