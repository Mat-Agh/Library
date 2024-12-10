package app.mat.library.feature.book.domain.usecase.local.favorite.delete

import app.mat.library.feature.book.domain.repository.BookRepository

class DeleteFavoriteBookLocalUseCaseImpl(
    private val bookRepository: BookRepository
) : DeleteFavoriteBookLocalUseCase {
    override suspend operator fun invoke(
        id: String
    ) = bookRepository.deleteFromFavoriteList(
        id = id
    )
}