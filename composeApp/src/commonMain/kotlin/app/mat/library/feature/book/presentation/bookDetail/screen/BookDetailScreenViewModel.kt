package app.mat.library.feature.book.presentation.bookDetail.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import app.mat.library.feature.book.data.mapper.bookMapper.toBookModel
import app.mat.library.feature.book.domain.usecase.local.description.GetBookDescriptionLocalUseCase
import app.mat.library.feature.book.domain.usecase.local.favorite.add.AddFavoriteBookLocalUseCase
import app.mat.library.feature.book.domain.usecase.local.favorite.checkStatus.CheckFavoriteBookStatusLocalUseCase
import app.mat.library.feature.book.domain.usecase.local.favorite.delete.DeleteFavoriteBookLocalUseCase
import app.mat.library.feature.book.presentation.bookDetail.action.BookDetailScreenAction
import app.mat.library.feature.book.presentation.bookDetail.state.BookDetailScreenState
import app.mat.library.feature.book.presentation.graph.BookGraph
import app.mat.library.feature.core.domain.type.onError
import app.mat.library.feature.core.domain.type.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailScreenViewModel(
    private val getBookDescriptionLocalUseCase: GetBookDescriptionLocalUseCase,
    private val checkFavoriteBookStatusLocalUseCase: CheckFavoriteBookStatusLocalUseCase,
    private val addFavoriteBookLocalUseCase: AddFavoriteBookLocalUseCase,
    private val deleteFavoriteBookLocalUseCase: DeleteFavoriteBookLocalUseCase,
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

                ""
            }
        )
    )

    val screenState: StateFlow<BookDetailScreenState> = _screenState.onStart {
        fetchBookDescription()

        observeFavoriteStatus()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(
            stopTimeoutMillis = 5000L
        ),
        initialValue = BookDetailScreenState.default
    )
    //endregion Variables

    //region Jobs
    private var favoriteStatusObserverJob: Job? = null
    private var fetchBookDescriptionJob: Job? = null
    //endregion Jobs

    //region Public Methods
    fun onAction(
        action: BookDetailScreenAction
    ) {
        when (action) {
            BookDetailScreenAction.OnFavoriteClicked -> {
                viewModelScope.launch(
                    Dispatchers.IO
                ) {
                    if (screenState.value.isFavorite) {
                        deleteFavoriteBookLocalUseCase(
                            id = screenState.value.bookWorkID
                        )
                    } else {
                        addFavoriteBookLocalUseCase(
                            bookModel = screenState.value.bookState.toBookModel()
                        )
                    }
                }
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
    }
    //endregion Public Methods

    //region Private Methods
    private fun observeFavoriteStatus() {
        favoriteStatusObserverJob?.cancel()

        favoriteStatusObserverJob = viewModelScope.launch(
            context = Dispatchers.IO
        ) {
            val bookId = _screenState.value.bookWorkID

            checkFavoriteBookStatusLocalUseCase(
                id = bookId
            ).onEach { isFavorite ->
                _screenState.update { screenState ->
                    screenState.copy(
                        isFavorite = isFavorite
                    )
                }
            }.launchIn(
                scope = viewModelScope
            )
        }
    }

    private fun fetchBookDescription() {
        fetchBookDescriptionJob?.cancel()

        fetchBookDescriptionJob = viewModelScope.launch(
            context = Dispatchers.IO
        ) {
            getBookDescriptionLocalUseCase(
                bookWorkId = _screenState.value.bookWorkID
            ).onSuccess { result ->
                _screenState.update { screenState ->
                    screenState.copy(
                        bookState = screenState.bookState.copy(
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