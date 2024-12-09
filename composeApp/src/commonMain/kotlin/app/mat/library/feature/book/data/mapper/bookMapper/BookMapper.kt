package app.mat.library.feature.book.data.mapper.bookMapper

import app.mat.library.feature.book.data.remote.dto.SearchedBookDto
import app.mat.library.feature.book.domain.model.BookModel
import app.mat.library.feature.book.presentation.state.BookState
import app.mat.library.feature.core.data.parameter.ServiceParameter

fun SearchedBookDto.toBookModel(): BookModel = BookModel(
    id = id.substringAfterLast(
        delimiter = "/"
    ),
    title = title,
    imageAddress = if (coverKey != null) {
        "${ServiceParameter.Core.BASE_OLD_IMAGE_URL}${coverKey}${ServiceParameter.Core.IMAGE_SIZE}${ServiceParameter.Core.IMAGE_FORMAT}"
    } else {
        "${ServiceParameter.Core.BASE_IMAGE_URL}${coverAlternativeKey}${ServiceParameter.Core.IMAGE_SIZE}${ServiceParameter.Core.IMAGE_FORMAT}"
    },
    authorList = authorNameList ?: emptyList(),
    description = null,
    languageList = languageList,
    firstPublishedYear = firstPublishedYear.toString(),
    averageRating = ratingAverage,
    ratingCount = ratingsCount,
    pageCount = numberPagesMedian,
    editionCount = editionsCount
)

fun BookModel.toBookState(): BookState = BookState(
    id = id,
    title = title,
    imageAddress = imageAddress,
    authorList = authorList,
    description = null,
    languageList = languageList,
    firstPublishedYear = firstPublishedYear.toString(),
    averageRating = averageRating,
    ratingCount = ratingCount,
    pageCount = pageCount,
    editionCount = editionCount
)