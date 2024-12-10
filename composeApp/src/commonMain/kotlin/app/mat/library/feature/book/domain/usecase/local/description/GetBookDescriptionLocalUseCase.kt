package app.mat.library.feature.book.domain.usecase.local.description

import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.domain.type.Result

interface GetBookDescriptionLocalUseCase {
    suspend operator fun invoke(
        bookWorkId: String
    ): Result<String?, DataError>
}