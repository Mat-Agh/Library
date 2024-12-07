package app.mat.library.feature.book.domain.model

data class BookModel(
    val id: String,
    val title: String?,
    val imageAddress: String?,
    val authorList: List<String>?,
    val description: String?,
    val languageList: List<String>?,
    val firstPublishedYear: String?,
    val averageRating: Double?,
    val ratingCount: Int?,
    val pageCount: Int?,
    val editionCount: Int?,
) {
    companion object {
        val default = BookModel(
            id = "",
            title = null,
            imageAddress = null,
            authorList = null,
            description = null,
            languageList = null,
            firstPublishedYear = null,
            averageRating = null,
            ratingCount = null,
            pageCount = null,
            editionCount = null
        )
    }
}
