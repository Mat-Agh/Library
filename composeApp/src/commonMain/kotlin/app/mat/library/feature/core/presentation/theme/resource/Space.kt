package app.mat.library.feature.core.presentation.theme.resource

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Space(
    val default: Dp = 0.dp,
    val minimum: Dp = 1.dp,
    val superSmall: Dp = 2.dp,
    val extraSmall: Dp = 4.dp,
    val smaller: Dp = 6.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 10.dp,
    val large: Dp = 12.dp,
    val larger: Dp = 14.dp,
    val extraLarge: Dp = 16.dp,
    val superLarge: Dp = 18.dp,
    val huge: Dp = 20.dp,
    val huger: Dp = 22.dp,
    val extraHuge: Dp = 24.dp,
    val superHuge: Dp = 26.dp,
    val titanic: Dp = 28.dp,
    val massive: Dp = 30.dp,
    val gigantic: Dp = 35.dp,
    val galactic: Dp = 40.dp,
    val universal: Dp = 45.dp,
    val multiversal: Dp = 50.dp,
    val hyperDimensional: Dp = 55.dp,
    val big: Dp = 60.dp,
    val bigger: Dp = 65.dp,
    val biggest: Dp = 70.dp,
    val extraBig: Dp = 75.dp,
    val superBig: Dp = 80.dp,
    val maximum: Dp = 90.dp
)

val LocalSpace = Space()

val space: Space
    get() = LocalSpace