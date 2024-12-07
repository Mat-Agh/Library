package app.mat.library.feature.book.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
    @SerialName(
        "docs"
    )
    val searchedBookList: List<SearchedBookDto>? = null
)
