package app.mat.library.feature.book.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedBookDto(
    @SerialName(
        value = "key"
    )
    val id: String,
    @SerialName(
        value = "title"
    )
    val title: String? = null,
    @SerialName(
        value = "language"
    )
    val languageList: List<String>? = null,
    @SerialName(
        value = "cover_i"
    )
    val coverAlternativeKey: Int? = null,
    @SerialName(
        value = "author_key"
    )
    val authorKeyList: List<String>? = null,
    @SerialName(
        value = "author_name"
    )
    val authorNameList: List<String>? = null,
    @SerialName(
        value = "cover_edition_key"
    )
    val coverKey: String? = null,
    @SerialName(
        value = "first_publish_year"
    )
    val firstPublishedYear: Int? = null,
    @SerialName(
        value = "ratings_average"
    )
    val ratingAverage: Double? = null,
    @SerialName(
        value = "ratings_count"
    )
    val ratingsCount: Int? = null,
    @SerialName(
        value = "number_of_pages_median"
    )
    val numberPagesMedian: Int? = null,
    @SerialName(
        value = "edition_count"
    )
    val editionsCount: Int? = null
)
