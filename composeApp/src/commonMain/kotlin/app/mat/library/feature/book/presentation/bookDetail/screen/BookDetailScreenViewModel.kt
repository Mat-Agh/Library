package app.mat.library.feature.book.presentation.bookDetail.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import app.mat.library.feature.book.domain.repository.BookRepository
import app.mat.library.feature.book.presentation.bookDetail.action.BookDetailScreenAction
import app.mat.library.feature.book.presentation.bookDetail.state.BookDetailScreenState
import app.mat.library.feature.book.presentation.graph.BookGraph
import app.mat.library.feature.core.domain.type.onError
import app.mat.library.feature.core.domain.type.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailScreenViewModel(
    private val bookRepository: BookRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    //region Variables
    private val _screenState: MutableStateFlow<BookDetailScreenState> = MutableStateFlow(
        value = BookDetailScreenState.default.copy(
            bookWorkID = try {
                savedStateHandle.toRoute<BookGraph.BookDetailScreen>().id
            } catch (
                exception: Exception
            ) {
                exception.printStackTrace()

                null
            }
        )
    )

    val screenState: StateFlow<BookDetailScreenState> = _screenState.onStart {
        fetchBookDescription()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(
            stopTimeoutMillis = 5000L
        ),
        initialValue = BookDetailScreenState.default
    )
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
    private fun fetchBookDescription() = viewModelScope.launch(
        context = Dispatchers.IO
    ) {
        _screenState.value.bookWorkID?.let { bookWorkId ->
            bookRepository.getBookDescription(
                bookWorkId = bookWorkId
            ).onSuccess { result ->
                _screenState.update { screenState ->
                    screenState.copy(
                        bookState = screenState.bookState?.copy(
                            description = result
                        ),
                        isLoading = false
                    )
                }
            }.onError {
                _screenState.update { screenState ->
                    screenState.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
    //endregion Private Methods
}