package app.mat.library.feature.book.domain.usecase.local.favorite.get

import app.mat.library.feature.book.domain.model.BookModel
import app.mat.library.feature.book.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteBookListLocalUseCaseImpl(
    private val bookRepository: BookRepository
) : GetFavoriteBookListLocalUseCase {
    override operator fun invoke(): Flow<List<BookModel>> = bookRepository.getFavoriteBookList()
}