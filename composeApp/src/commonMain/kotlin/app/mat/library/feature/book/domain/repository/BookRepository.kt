package app.mat.library.feature.book.domain.repository

import app.mat.library.feature.book.domain.model.BookModel
import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.domain.type.Result

interface BookRepository {
    suspend fun searchRemote(
        query: String
    ): Result<List<BookModel>, DataError.Remote>
}