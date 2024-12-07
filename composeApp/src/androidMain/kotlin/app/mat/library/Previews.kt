package app.mat.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.mat.library.feature.book.presentation.bookList.screen.BookListScreen
import app.mat.library.feature.book.presentation.bookList.state.BookListScreenState
import app.mat.library.feature.book.presentation.state.BookState
import app.mat.library.feature.core.presentation.component.search.SearchBarComponent

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFF
)
@Composable
private fun AppPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ) {
            SearchBarComponent(
                searchQuery = "",
                onSearchQueryChanged = {},
                onKeyboardSearchClicked = {},
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFF
)
@Composable
private fun BookListScreenPreview() {
    val bookStateList = (0..100).map {
        BookState(
            id = it.toString(),
            title = "Book $it",
            imageAddress = "https://test.com",
            authorList = listOf(
                "Author $it",
                "Author $it"
            ),
            description = "Description $it",
            languageList = emptyList(),
            firstPublishedYear = null,
            pageCount = 100,
            editionCount = 3,
            averageRating = 4.46546,
            ratingCount = 10
        )
    }

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ) {
            BookListScreen(
                stateProvider = {
                    BookListScreenState.default.copy(
                        searchResultList = bookStateList
                    )
                },
                onAction = {

                }
            )
        }
    }
}