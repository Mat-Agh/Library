package app.mat.library.feature.book.presentation.bookDetail.state

import androidx.compose.runtime.Immutable
import app.mat.library.feature.book.presentation.state.BookState

@Immutable
data class BookDetailScreenState(
    val isLoading: Boolean,
    val isFavorite: Boolean,
    val bookState: BookState?
) {
    companion object {
        val default = BookDetailScreenState(
            isLoading = true,
            isFavorite = false,
            bookState = null
        )
    }
}