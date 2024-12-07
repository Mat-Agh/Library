package app.mat.library.feature.book.presentation.bookList.action

import app.mat.library.feature.book.presentation.state.BookState

sealed interface BookListScreenAction {
    data class OnSearchQueryChanged(
        val searchQuery: String
    ) : BookListScreenAction

    data class OnBookClicked(
        val bookState: BookState
    ) : BookListScreenAction

    data class OnTabSelected(
        val selectedTabIndex: Int
    ) : BookListScreenAction
}