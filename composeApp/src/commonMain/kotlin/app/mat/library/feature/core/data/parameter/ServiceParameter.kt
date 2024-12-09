package app.mat.library.feature.core.data.parameter

import io.ktor.client.plugins.logging.LogLevel

data object ServiceParameter {
    data object Core {
        const val BASE_API_URL = "https://openlibrary.org/"
        const val BASE_OLD_IMAGE_URL = "https://covers.openlibrary.org/b/olid/"
        const val BASE_IMAGE_URL = "https://covers.openlibrary.org/b/id/"
        const val IMAGE_SIZE = "-L"
        const val IMAGE_FORMAT = ".jpg"
        const val REQUEST_TIMEOUT = 15_000L
        val LOGGING_LEVEL = LogLevel.ALL
    }

    data object Search {
        const val PATH = "search.json/"
    }

    data object BookWork {
        const val ROUTE = "works/"
        const val EXTENSION = ".json"
    }
}