package app.mat.library.feature.core.presentation.theme.resource

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class ShadowColor(
    val default: Color = Color.Transparent,
    val extraSmall: Color = Color(
        color = 0xFF000000,
    ).copy(
        alpha = 0.6f
    ),
    val small: Color = Color(
        color = 0xFF000000,
    ).copy(
        alpha = 0.7f
    ),
    val medium: Color = Color(
        color = 0xFF000000,
    ).copy(
        alpha = 0.8f
    ),
    val large: Color = Color(
        color = 0xFF000000,
    ).copy(
        alpha = 0.9f
    ),
    val extraLarge: Color = Color(
        color = 0xFF000000,
    )
)


val LocalShadow = compositionLocalOf {
    ShadowColor()
}

val MaterialTheme.shadowColor: ShadowColor
    @Composable
    get() = LocalShadow.current