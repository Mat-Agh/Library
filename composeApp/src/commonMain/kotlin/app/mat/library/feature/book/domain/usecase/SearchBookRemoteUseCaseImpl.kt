package app.mat.library.feature.book.domain.usecase

import app.mat.library.feature.book.domain.model.BookModel
import app.mat.library.feature.book.domain.repository.BookRepository
import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.domain.type.Result

class SearchBookRemoteUseCaseImpl(
    private val bookRepository: BookRepository
) : SearchBookRemoteUseCase {
    override suspend fun invoke(
        query: String
    ): Result<List<BookModel>, DataError.Remote> = bookRepository.searchRemote(
        query = query
    )
}