package app.mat.library.feature.book.domain.usecase.local.favorite.delete

interface DeleteFavoriteBookLocalUseCase {
    suspend operator fun invoke(
        id: String
    )
}