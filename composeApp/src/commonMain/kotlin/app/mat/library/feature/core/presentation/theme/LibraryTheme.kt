package app.mat.library.feature.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import app.mat.library.feature.core.presentation.theme.resource.Shape
import app.mat.library.feature.core.presentation.theme.resource.Typography

@Composable
fun LibraryRootTheme(
    showInDarkThemeStateProvider: () -> Boolean = {
        false
    },
    languageTypeProvider: () -> String = {
        "en"
    },
    content: @Composable () -> Unit,
) {
    val languageTypeState by rememberUpdatedState(
        newValue = languageTypeProvider()
    )

    val typographyState by rememberUpdatedState(
        newValue = Typography
    )

    val layoutDirectionState by remember {
        derivedStateOf {
            when (languageTypeState) {
                "en" -> LayoutDirection.Ltr

                else -> LayoutDirection.Rtl
            }
        }
    }

    LibraryThemeContent(
        layoutDirectionStateProvider = {
            layoutDirectionState
        },
        typographyStateProvider = {
            typographyState
        },
        content = {
            content()
        }
    )
}

@Composable
private fun LibraryThemeContent(
    layoutDirectionStateProvider: () -> LayoutDirection,
    typographyStateProvider: () -> Typography,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalLayoutDirection provides layoutDirectionStateProvider()
    ) {
        MaterialTheme(
            typography = typographyStateProvider(),
            shapes = Shape
        ) {
            content()
        }
    }
}