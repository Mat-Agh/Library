package app.mat.library.feature.book.domain.usecase.local.favorite.add

import app.mat.library.feature.book.domain.model.BookModel
import app.mat.library.feature.book.domain.repository.BookRepository
import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.domain.type.Result

class AddFavoriteBookLocalUseCaseImpl(
    private val bookRepository: BookRepository
) : AddFavoriteBookLocalUseCase {
    override suspend operator fun invoke(
        bookModel: BookModel
    ): Result<Unit, DataError.Local> = bookRepository.addToFavoriteList(
        bookModel = bookModel
    )
}