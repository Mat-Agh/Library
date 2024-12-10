package app.mat.library.feature.book.domain.repository

import app.mat.library.feature.book.domain.model.BookModel
import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.domain.type.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchRemote(
        query: String
    ): Result<List<BookModel>, DataError.Remote>

    suspend fun getBookDescription(
        bookWorkId: String
    ): Result<String?, DataError>

    fun getFavoriteBookList(): Flow<List<BookModel>>

    fun isBookFavorite(
        id: String
    ): Flow<Boolean>

    suspend fun addToFavoriteList(
        bookModel: BookModel
    ): Result<Unit, DataError.Local>

    suspend fun deleteFromFavoriteList(
        id: String
    )
}