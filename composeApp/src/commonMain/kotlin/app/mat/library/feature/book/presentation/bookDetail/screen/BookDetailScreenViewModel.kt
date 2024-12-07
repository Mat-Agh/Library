package app.mat.library.feature.book.presentation.bookDetail.screen

import androidx.lifecycle.ViewModel
import app.mat.library.feature.book.presentation.bookDetail.action.BookDetailScreenAction
import app.mat.library.feature.book.presentation.bookDetail.state.BookDetailScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookDetailScreenViewModel : ViewModel() {
    //region Variables
    private val _screenState: MutableStateFlow<BookDetailScreenState> = MutableStateFlow(
        value = BookDetailScreenState.default
    )

    val screenState: StateFlow<BookDetailScreenState> = _screenState.asStateFlow()
    //endregion Variables

    //region Public Methods
    fun onAction(
        action: BookDetailScreenAction
    ) = when (action) {
        BookDetailScreenAction.OnFavoriteClicked -> {

        }

        is BookDetailScreenAction.OnSelectedBookChanged -> {
            _screenState.update { screenState ->
                screenState.copy(
                    bookState = action.bookState
                )
            }
        }

        else -> Unit
    }
    //endregion Public Methods

    //region Private Methods

    //endregion Private Methods
}