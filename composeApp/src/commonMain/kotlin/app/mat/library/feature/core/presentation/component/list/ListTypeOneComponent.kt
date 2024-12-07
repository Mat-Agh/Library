package app.mat.library.feature.core.presentation.component.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.mat.library.feature.book.presentation.state.BookState
import app.mat.library.feature.core.presentation.component.listItem.ListItemTypeOneComponent

@Composable
fun ListTypeOneComponent(
    bookStateListProvider: () -> List<BookState>,
    onBookClicked: (BookState) -> Unit,
    modifier: Modifier = Modifier,
    scrollStateProvider: @Composable () -> LazyListState = {
        rememberLazyListState()
    }
) {
    LazyColumn(
        modifier = modifier,
        state = scrollStateProvider(),
        verticalArrangement = Arrangement.spacedBy(
            space = 12.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = bookStateListProvider(),
            key = {
                it.id
            }
        ) { bookState ->
            ListItemTypeOneComponent(
                bookState = bookState,
                onBookClicked = {
                    onBookClicked(
                        bookState
                    )
                },
                modifier = Modifier
                    .widthIn(
                        max = 700.dp
                    )
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp
                    )
            )
        }
    }
}