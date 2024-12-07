package app.mat.library.feature.book.presentation.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mat.library.feature.book.presentation.state.BookState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    //region Variables
    private val _selectedBookState: MutableStateFlow<BookState?> = MutableStateFlow(
        value = BookState.default
    )

    val selectedBookState: StateFlow<BookState?>
        get() = _selectedBookState.asStateFlow()
    //endregion Variables

    //region Public Methods
    fun onBookSelected(
        bookState: BookState?
    ) = viewModelScope.launch(
        context = Dispatchers.IO
    ) {
        _selectedBookState.emit(
            value = bookState
        )
    }
    //endregion Public Methods
}