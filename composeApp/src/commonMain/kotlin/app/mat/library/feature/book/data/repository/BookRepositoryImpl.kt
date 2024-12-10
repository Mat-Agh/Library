package app.mat.library.feature.book.data.repository

import androidx.sqlite.SQLiteException
import app.mat.library.feature.book.data.local.dao.FavoriteBookDao
import app.mat.library.feature.book.data.mapper.bookMapper.toBookModel
import app.mat.library.feature.book.data.mapper.bookMapper.toFavoriteBookEntity
import app.mat.library.feature.book.data.remote.network.dataSource.BookRemoteDataSource
import app.mat.library.feature.book.domain.model.BookModel
import app.mat.library.feature.book.domain.repository.BookRepository
import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.domain.type.Result
import app.mat.library.feature.core.domain.type.map
import app.mat.library.feature.core.domain.type.onError
import app.mat.library.feature.core.domain.type.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(
    private val bookRemoteDataSource: BookRemoteDataSource,
    private val favoriteBookDao: FavoriteBookDao
) : BookRepository {
    override suspend fun searchRemote(
        query: String
    ): Result<List<BookModel>, DataError.Remote> = bookRemoteDataSource.search(
        query = query
    ).map { dto ->
        dto.searchedBookList?.map { searchedBookDto ->
            searchedBookDto.toBookModel()
        } ?: emptyList()
    }

    override suspend fun getBookDescription(
        bookWorkId: String
    ): Result<String?, DataError> {
        val localResult = favoriteBookDao.getFavoriteBook(
            id = bookWorkId
        )

        val hasDescription: Boolean = !localResult?.description.isNullOrBlank()

        return if (localResult == null) {
            bookRemoteDataSource.getBookDetails(
                bookWorkId = bookWorkId
            ).map { dto ->
                dto.description
            }
        } else if (!hasDescription) {
            bookRemoteDataSource.getBookDetails(
                bookWorkId = bookWorkId
            ).map { dto ->
                dto.description
            }.onSuccess { description ->
                favoriteBookDao.upsert(
                    favoriteBookEntity = localResult.copy(
                        description = description
                    )
                )

                Result.Success(
                    data = description
                )
            }.onError { error ->
                Result.Error(
                    error = error
                )
            }
        } else {
            Result.Success(
                data = localResult.description
            )
        }
    }

    override fun getFavoriteBookList(): Flow<List<BookModel>> = favoriteBookDao.getFavoriteBookList().map { bookEntityList ->
        bookEntityList.map {
            it.toBookModel()
        }
    }

    override fun isBookFavorite(
        id: String
    ): Flow<Boolean> = favoriteBookDao.getFavoriteBookList().map { bookEntityList ->
        bookEntityList.any { bookEntity ->
            bookEntity.id == id
        }
    }

    override suspend fun addToFavoriteList(
        bookModel: BookModel
    ): Result<Unit, DataError.Local> = try {
        favoriteBookDao.upsert(
            bookModel.toFavoriteBookEntity()
        )

        Result.Success(
            data = Unit
        )
    } catch (
        exception: SQLiteException
    ) {
        Result.Error(
            error = DataError.Local.DISK_FULL
        )
    }

    override suspend fun deleteFromFavoriteList(
        id: String
    ) = favoriteBookDao.deleteFavoriteBook(
        id = id
    )
}