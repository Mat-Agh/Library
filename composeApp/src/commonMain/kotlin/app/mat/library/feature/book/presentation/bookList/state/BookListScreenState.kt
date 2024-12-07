package app.mat.library.feature.book.presentation.bookList.state

import androidx.compose.runtime.Immutable
import app.mat.library.feature.book.presentation.state.BookState
import app.mat.library.feature.core.presentation.type.UiText

@Immutable
data class BookListScreenState(
    val query: String = "",
    val searchResultList: List<BookState>,
    val favoriteBookList: List<BookState>,
    val isLoading: Boolean,
    val selectedTabIndex: Int,
    val errorMessage: UiText?
) {
    companion object {
        val default = BookListScreenState(
            query = "Kotlin",
            searchResultList = emptyList(),
            favoriteBookList = emptyList(),
            isLoading = true,
            selectedTabIndex = 0,
            errorMessage = null
        )
    }
}
