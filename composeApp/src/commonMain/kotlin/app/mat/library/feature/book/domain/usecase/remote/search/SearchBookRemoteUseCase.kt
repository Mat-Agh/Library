package app.mat.library.feature.book.domain.usecase.remote.search

import app.mat.library.feature.book.domain.model.BookModel
import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.domain.type.Result

interface SearchBookRemoteUseCase {
    suspend operator fun invoke(
        query: String
    ): Result<List<BookModel>, DataError.Remote>
}