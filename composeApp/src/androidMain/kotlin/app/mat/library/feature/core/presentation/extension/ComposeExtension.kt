package app.mat.library.feature.core.presentation.extension

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class ComposeExtension {
    @Stable
    fun Modifier.shadow(
        elevation: Dp,
        shape: Shape = RectangleShape,
        clip: Boolean = elevation > 0.dp,
        ambientColor: Color = DefaultShadowColor,
        spotColor: Color = DefaultShadowColor,
    ): Modifier = if (VERSION.SDK_INT >= VERSION_CODES.P) {
        then(
            other = Modifier.shadow(
                elevation = elevation,
                shape = shape,
                clip = clip,
                ambientColor = ambientColor,
                spotColor = spotColor
            )
        )
    } else {
        then(
            other = Modifier.shadow(
                elevation = elevation,
                shape = shape,
                clip = clip
            )
        )
    }
}