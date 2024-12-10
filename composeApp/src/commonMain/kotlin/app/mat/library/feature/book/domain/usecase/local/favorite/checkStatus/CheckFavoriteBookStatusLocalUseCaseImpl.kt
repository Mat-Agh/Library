package app.mat.library.feature.book.domain.usecase.local.favorite.checkStatus

import app.mat.library.feature.book.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class CheckFavoriteBookStatusLocalUseCaseImpl(
    private val bookRepository: BookRepository
) : CheckFavoriteBookStatusLocalUseCase {
    override operator fun invoke(
        id: String
    ): Flow<Boolean> = bookRepository.isBookFavorite(
        id = id
    )
}