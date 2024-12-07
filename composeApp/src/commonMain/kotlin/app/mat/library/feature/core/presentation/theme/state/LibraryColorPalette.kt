package app.mat.library.feature.core.presentation.theme.state

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import app.mat.library.feature.core.presentation.extension.animateColor

@Immutable
data class LibraryColorPalette(
    val colorScheme: ColorScheme,
    val warning: Color,
    val warningContainer: Color,
    val onWarning: Color,
    val onWarningContainer: Color,
    val information: Color,
    val informationContainer: Color,
    val onInformation: Color,
    val onInformationContainer: Color
) {
    val primary: Color
        get() = colorScheme.primary
    val onPrimary: Color
        get() = colorScheme.onPrimary
    val primaryContainer: Color
        get() = colorScheme.primaryContainer
    val onPrimaryContainer: Color
        get() = colorScheme.onPrimaryContainer
    val inversePrimary: Color
        get() = colorScheme.inversePrimary
    val secondary: Color
        get() = colorScheme.secondary
    val onSecondary: Color
        get() = colorScheme.onSecondary
    val secondaryContainer: Color
        get() = colorScheme.secondaryContainer
    val onSecondaryContainer: Color
        get() = colorScheme.onSecondaryContainer
    val tertiary: Color
        get() = colorScheme.tertiary
    val onTertiary: Color
        get() = colorScheme.onTertiary
    val tertiaryContainer: Color
        get() = colorScheme.tertiaryContainer
    val onTertiaryContainer: Color
        get() = colorScheme.onTertiaryContainer
    val background: Color
        get() = colorScheme.background
    val onBackground: Color
        get() = colorScheme.onBackground
    val surface: Color
        get() = colorScheme.surface
    val onSurface: Color
        get() = colorScheme.onSurface
    val surfaceVariant: Color
        get() = colorScheme.surfaceVariant
    val onSurfaceVariant: Color
        get() = colorScheme.onSurfaceVariant
    val surfaceTint: Color
        get() = colorScheme.surfaceTint
    val inverseSurface: Color
        get() = colorScheme.inverseSurface
    val inverseOnSurface: Color
        get() = colorScheme.inverseOnSurface
    val error: Color
        get() = colorScheme.error
    val onError: Color
        get() = colorScheme.onError
    val errorContainer: Color
        get() = colorScheme.errorContainer
    val onErrorContainer: Color
        get() = colorScheme.onErrorContainer
    val outline: Color
        get() = colorScheme.outline
    val outlineVariant: Color
        get() = colorScheme.outlineVariant
    val scrim: Color
        get() = colorScheme.scrim
    val surfaceBright: Color
        get() = colorScheme.surfaceBright
    val surfaceDim: Color
        get() = colorScheme.surfaceDim
    val surfaceContainer: Color
        get() = colorScheme.surfaceContainer
    val surfaceContainerHigh: Color
        get() = colorScheme.surfaceContainerHigh
    val surfaceContainerHighest: Color
        get() = colorScheme.surfaceContainerHighest
    val surfaceContainerLow: Color
        get() = colorScheme.surfaceContainerLow
    val surfaceContainerLowest: Color
        get() = colorScheme.surfaceContainerLowest

    @Composable
    fun animated() = copy(
        colorScheme = colorScheme.copy(
            primary = animateColor(
                targetValue = colorScheme.primary
            ),
            onPrimary = animateColor(
                targetValue = colorScheme.onPrimary
            ),
            primaryContainer = animateColor(
                targetValue = colorScheme.primaryContainer
            ),
            onPrimaryContainer = animateColor(
                targetValue = colorScheme.onPrimaryContainer
            ),
            inversePrimary = animateColor(
                targetValue = colorScheme.inversePrimary
            ),
            secondary = animateColor(
                targetValue = colorScheme.secondary
            ),
            onSecondary = animateColor(
                targetValue = colorScheme.onSecondary
            ),
            secondaryContainer = animateColor(
                targetValue = colorScheme.secondaryContainer
            ),
            onSecondaryContainer = animateColor(
                targetValue = colorScheme.onSecondaryContainer
            ),
            tertiary = animateColor(
                targetValue = colorScheme.tertiary
            ),
            onTertiary = animateColor(
                targetValue = colorScheme.onTertiary
            ),
            tertiaryContainer = animateColor(
                targetValue = colorScheme.tertiaryContainer
            ),
            onTertiaryContainer = animateColor(
                targetValue = colorScheme.onTertiaryContainer
            ),
            background = animateColor(
                targetValue = colorScheme.background
            ),
            onBackground = animateColor(
                targetValue = colorScheme.onBackground
            ),
            surface = animateColor(
                targetValue = colorScheme.surface
            ),
            onSurface = animateColor(
                targetValue = colorScheme.onSurface
            ),
            surfaceVariant = animateColor(
                targetValue = colorScheme.surfaceVariant
            ),
            onSurfaceVariant = animateColor(
                targetValue = colorScheme.onSurfaceVariant
            ),
            surfaceTint = animateColor(
                targetValue = colorScheme.surfaceTint
            ),
            inverseSurface = animateColor(
                targetValue = colorScheme.inverseSurface
            ),
            inverseOnSurface = animateColor(
                targetValue = colorScheme.inverseOnSurface
            ),
            error = animateColor(
                targetValue = colorScheme.error
            ),
            onError = animateColor(
                targetValue = colorScheme.onError
            ),
            errorContainer = animateColor(
                targetValue = colorScheme.errorContainer
            ),
            onErrorContainer = animateColor(
                targetValue = colorScheme.onErrorContainer
            ),
            outline = animateColor(
                targetValue = colorScheme.outline
            ),
            outlineVariant = animateColor(
                targetValue = colorScheme.outlineVariant
            ),
            scrim = animateColor(
                targetValue = colorScheme.scrim
            ),
            surfaceBright = animateColor(
                targetValue = colorScheme.surfaceBright
            ),
            surfaceDim = animateColor(
                targetValue = colorScheme.surfaceDim
            ),
            surfaceContainer = animateColor(
                targetValue = colorScheme.surfaceContainer
            ),
            surfaceContainerHigh = animateColor(
                targetValue = colorScheme.surfaceContainerHigh
            ),
            surfaceContainerHighest = animateColor(
                targetValue = colorScheme.surfaceContainerHighest
            ),
            surfaceContainerLow = animateColor(
                targetValue = colorScheme.surfaceContainerLow
            ),
            surfaceContainerLowest = animateColor(
                targetValue = colorScheme.surfaceContainerLowest
            )
        ),
        warning = animateColor(
            targetValue = warning
        ),
        warningContainer = animateColor(
            targetValue = warningContainer
        ),
        onWarning = animateColor(
            targetValue = onWarning
        ),
        onWarningContainer = animateColor(
            targetValue = onWarningContainer
        ),
        information = animateColor(
            targetValue = information
        ),
        informationContainer = animateColor(
            targetValue = informationContainer
        ),
        onInformation = animateColor(
            targetValue = onInformation
        ),
        onInformationContainer = animateColor(
            targetValue = onInformationContainer
        )
    )
}
