package app.mat.library.feature.book.presentation.bookList.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mat.library.feature.book.data.mapper.BookMapper.toBookState
import app.mat.library.feature.book.domain.usecase.SearchBookRemoteUseCase
import app.mat.library.feature.book.presentation.bookList.action.BookListScreenAction
import app.mat.library.feature.book.presentation.bookList.state.BookListScreenState
import app.mat.library.feature.book.presentation.state.BookState
import app.mat.library.feature.core.domain.type.onError
import app.mat.library.feature.core.domain.type.onSuccess
import app.mat.library.feature.core.presentation.extension.toUiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListScreenViewModel(
    private val searchBookRemoteUseCase: SearchBookRemoteUseCase
) : ViewModel() {
    //region Variables
    private val _cachedBookList: MutableStateFlow<List<BookState>> = MutableStateFlow(
        value = emptyList()
    )

    private val _screenState: MutableStateFlow<BookListScreenState> = MutableStateFlow(
        value = BookListScreenState.default
    )

    val screenState: StateFlow<BookListScreenState> = _screenState
        .onStart {
            if (_cachedBookList.value.isEmpty()) {
                observeSearchQueryChanges()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(
                stopTimeoutMillis = 5000L
            ),
            initialValue = _screenState.value

        )
    //endregion Variables

    //region Jobs
    private var searchJob: Job? = null
    //endregion Jobs

    //region Public Methods
    fun onAction(
        action: BookListScreenAction
    ) = when (action) {
        is BookListScreenAction.OnBookClicked -> {

        }

        is BookListScreenAction.OnSearchQueryChanged -> {
            _screenState.update { screenState ->
                screenState.copy(
                    query = action.searchQuery
                )
            }
        }

        is BookListScreenAction.OnTabSelected -> {
            _screenState.update { screenState ->
                screenState.copy(
                    selectedTabIndex = action.selectedTabIndex
                )
            }
        }
    }
    //endregion Public Methods

    //region Private Methods
    @OptIn(FlowPreview::class)
    private fun observeSearchQueryChanges() {
        screenState
            .map {
                it.query
            }.distinctUntilChanged()
            .debounce(
                timeoutMillis = 500L
            ).onEach { query ->
                when {
                    query.isBlank() -> {
                        _screenState.update { screenState ->
                            screenState.copy(
                                errorMessage = null,
                                searchResultList = _cachedBookList.value
                            )
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()

                        searchJob = search(
                            query = query
                        )
                    }
                }
            }
            .launchIn(
                scope = viewModelScope
            )
    }

    private fun search(
        query: String
    ) = viewModelScope.launch(
        context = Dispatchers.IO
    ) {
        _screenState.update { screenState ->
            screenState.copy(
                isLoading = true
            )
        }

        searchBookRemoteUseCase(
            query = query
        ).onSuccess { searchResultList ->
            val bookStateList = searchResultList.map { bookModel ->
                bookModel.toBookState()
            }

            _cachedBookList.update {
                bookStateList
            }

            _screenState.update { screenState ->
                screenState.copy(
                    isLoading = false,
                    errorMessage = null,
                    searchResultList = bookStateList
                )
            }
        }.onError { error ->
            _screenState.update { screenState ->
                screenState.copy(
                    searchResultList = emptyList(),
                    isLoading = false,
                    errorMessage = error.toUiText()
                )
            }
        }
    }
    //endregion Private Methods
}