package dev.vrba.slack.service.storage

data class Results(
    val index: Int = 0,
    val gifs: List<String> = emptyList(),
) {
    val current: String
        get() = gifs[index % gifs.size]
}
