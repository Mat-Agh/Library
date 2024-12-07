package app.mat.library.feature.core.domain.type

sealed interface DataError : Error {
    enum class Remote : DataError {
        TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local : DataError {
        DISK_FULL,
        UNKNOWN
    }
}
