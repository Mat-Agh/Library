package app.mat.library.feature.book.domain.usecase.local.favorite.add

import app.mat.library.feature.book.domain.model.BookModel
import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.domain.type.Result

interface AddFavoriteBookLocalUseCase {
    suspend operator fun invoke(
        bookModel: BookModel
    ): Result<Unit, DataError.Local>
}