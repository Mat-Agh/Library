package app.mat.library.feature.core.presentation.theme.resource

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes.default: CornerBasedShape
    get() = RoundedCornerShape(
        size = 0.dp
    )

val Shapes.superLarge: CornerBasedShape
    get() = RoundedCornerShape(
        size = 36.dp
    )

val Shapes.circle: CornerBasedShape
    get() = RoundedCornerShape(
        size = 50.dp
    )

val Shapes.extraLargeBottom: CornerBasedShape
    get() = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 24.dp,
        bottomEnd = 24.dp
    )

val Shapes.extraLargeTop: CornerBasedShape
    get() = RoundedCornerShape(
        topStart = 24.dp,
        topEnd = 24.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

val Shapes.largerTop: CornerBasedShape
    get() = RoundedCornerShape(
        topStart = 14.dp,
        topEnd = 14.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )


val Shape = Shapes(
    extraLarge = RoundedCornerShape(
        size = 24.dp
    ),
    large = RoundedCornerShape(
        size = 12.dp
    ),
    medium = RoundedCornerShape(
        size = 10.dp
    ),
    small = RoundedCornerShape(
        size = 8.dp
    ),
    extraSmall = RoundedCornerShape(
        size = 4.dp
    )
)