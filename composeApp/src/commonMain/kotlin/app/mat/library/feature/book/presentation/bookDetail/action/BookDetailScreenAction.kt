package app.mat.library.feature.book.presentation.bookDetail.action

import app.mat.library.feature.book.presentation.state.BookState

sealed interface BookDetailScreenAction {
    data object OnBackClicked : BookDetailScreenAction

    data object OnFavoriteClicked : BookDetailScreenAction

    data class OnSelectedBookChanged(
        val bookState: BookState
    ) : BookDetailScreenAction
}