package app.mat.library.feature.core.presentation.component.container

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TitledContent(
    modifier: Modifier = Modifier,
    titleProvider: @Composable () -> String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titleProvider(),
            style = MaterialTheme.typography.titleSmall
        )

        content()
    }
}