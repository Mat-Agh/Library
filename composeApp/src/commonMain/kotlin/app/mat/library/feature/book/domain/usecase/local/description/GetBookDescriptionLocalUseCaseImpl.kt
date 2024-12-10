package app.mat.library.feature.book.domain.usecase.local.description

import app.mat.library.feature.book.domain.repository.BookRepository
import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.domain.type.Result

class GetBookDescriptionLocalUseCaseImpl(
    private val bookRepository: BookRepository
) : GetBookDescriptionLocalUseCase {
    override suspend operator fun invoke(
        bookWorkId: String
    ): Result<String?, DataError> = bookRepository.getBookDescription(
        bookWorkId = bookWorkId
    )
}