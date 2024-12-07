package app.mat.library.feature.core.presentation.component.progress

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import app.mat.library.feature.core.presentation.theme.resource.SandYellow

@Composable
fun PulseProgress(
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition()

    val progressionState by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = progressionState
                scaleY = progressionState
                alpha = 1f - progressionState
            }
            .border(
                width = 5.dp,
                color = SandYellow,
                shape = CircleShape
            )
    )
}