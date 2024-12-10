package app.mat.library.feature.book.domain.usecase.local.favorite.get

import app.mat.library.feature.book.domain.model.BookModel
import kotlinx.coroutines.flow.Flow

interface GetFavoriteBookListLocalUseCase {
    operator fun invoke(): Flow<List<BookModel>>
}