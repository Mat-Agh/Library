package app.mat.library.feature.book.data.repository

import app.mat.library.feature.book.data.mapper.bookMapper.toBookModel
import app.mat.library.feature.book.data.remote.network.dataSource.BookRemoteDataSource
import app.mat.library.feature.book.domain.model.BookModel
import app.mat.library.feature.book.domain.repository.BookRepository
import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.domain.type.Result
import app.mat.library.feature.core.domain.type.map

class BookRepositoryImpl(
    private val bookRemoteDataSource: BookRemoteDataSource
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
    ): Result<String?, DataError> = bookRemoteDataSource.getBookDetails(
        bookWorkId = bookWorkId
    ).map {
        it.description
    }
}