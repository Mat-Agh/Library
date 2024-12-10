package app.mat.library.feature.book.domain.usecase.local.favorite.checkStatus

import kotlinx.coroutines.flow.Flow

interface CheckFavoriteBookStatusLocalUseCase {
    operator fun invoke(
        id: String
    ): Flow<Boolean>
}