package app.mat.library.feature.book.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteBookEntity(
    @PrimaryKey(
        autoGenerate = false
    ) val id: String,
    val title: String?,
    val description: String?,
    val imageAddress: String?,
    val languageList: List<String>?,
    val authorList: List<String>?,
    val firstPublishedYear: String?,
    val ratingsAverage: Double?,
    val ratingsCount: Int?,
    val pageCount: Int?,
    val numEditions: Int?
)
