package app.mat.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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