package app.mat.library.feature.core.presentation.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import app.mat.library.feature.core.presentation.theme.resource.LightBlue
import app.mat.library.feature.core.presentation.type.ChipSize

@Composable
fun ChipTypeOne(
    modifier: Modifier = Modifier,
    chipSizeProvider: () -> ChipSize,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .widthIn(
                min = when (chipSizeProvider()) {
                    ChipSize.SMALL -> 50.dp
                    ChipSize.MEDIUM -> 80.dp
                    ChipSize.BIG -> 100.dp
                }
            )
            .clip(
                shape = RoundedCornerShape(
                    size = 16.dp
                )
            )
            .background(
                color = LightBlue
            )
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            content()
        }
    }
}