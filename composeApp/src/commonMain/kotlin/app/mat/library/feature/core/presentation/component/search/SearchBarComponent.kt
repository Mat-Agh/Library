package app.mat.library.feature.core.presentation.component.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import app.mat.library.feature.core.presentation.theme.resource.DarkBlue
import app.mat.library.feature.core.presentation.theme.resource.DesertWhite
import app.mat.library.feature.core.presentation.theme.resource.SandYellow
import library.composeapp.generated.resources.Res
import library.composeapp.generated.resources.field_hint_search
import library.composeapp.generated.resources.ui_hint_clear_search_field
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchBarComponent(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onKeyboardSearchClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = SandYellow,
            backgroundColor = SandYellow.copy(
                alpha = 0.4f
            )
        ),
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            shape = RoundedCornerShape(
                percent = 100
            ),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = DarkBlue,
                focusedBorderColor = SandYellow
            ),
            placeholder = {
                Text(
                    text = stringResource(
                        Res.string.field_hint_search
                    )
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.66f
                    )
                )
            },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    onKeyboardSearchClicked()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            trailingIcon = {
                AnimatedVisibility(
                    visible = searchQuery.isNotBlank()
                ) {
                    IconButton(
                        onClick = {
                            onSearchQueryChanged("")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(Res.string.ui_hint_clear_search_field),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            },
            modifier = modifier
                .background(
                    shape = RoundedCornerShape(
                        percent = 100
                    ),
                    color = DesertWhite
                )
                .minimumInteractiveComponentSize()
        )
    }
}