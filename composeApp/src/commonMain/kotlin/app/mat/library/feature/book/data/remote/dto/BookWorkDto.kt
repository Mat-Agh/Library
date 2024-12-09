package app.mat.library.feature.book.data.remote.dto

import app.mat.library.feature.book.data.remote.serializer.BookWorkDtoSerializer
import kotlinx.serialization.Serializable

@Serializable(
    with = BookWorkDtoSerializer::class
)
data class BookWorkDto(
    val description: String? = null
)
