package dev.vrba.slack.service.tenor

import dev.vrba.slack.service.tenor.dto.TenorSearchResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client(id = "tenor")
interface TenorApiClient {
    @Get("/v2/search")
    suspend fun search(
        @QueryValue("q") q: String,
        @QueryValue("limit") limit: Int = 20,
        @QueryValue("key") key: String = "",
    ): TenorSearchResponse
}
