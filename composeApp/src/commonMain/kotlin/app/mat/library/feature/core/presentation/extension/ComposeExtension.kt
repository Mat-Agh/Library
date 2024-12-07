package app.mat.library.feature.core.presentation.extension

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Typeface
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.times
import app.mat.library.feature.core.presentation.parameter.AnimationParameter
import app.mat.library.feature.core.presentation.theme.resource.space
import kotlinx.coroutines.delay

@Composable
fun animateColor(
    targetValue: Color
) = animateColorAsState(
    targetValue = targetValue,
    animationSpec = colorChangeAnimationSpec(
        durationMillis = AnimationParameter.Basic.LONG_COLOR_CHANGE_DURATION
    ),
    label = "Static color animator"
).value

@Stable
fun <T> List<T>?.measureLazyHeight(
    singleItemHeight: Dp,
    spaceBetween: Dp,
    columnCount: Int,
): Dp {
    if (this.isNullOrEmpty()) return space.default

    val division: Double = (this.size.toDouble() / columnCount.toDouble())

    val remaining: Double = (this.size.toDouble() % columnCount.toDouble())

    val rows = division.toInt() + if (remaining > 0) 1 else 0

    return (rows * singleItemHeight) + (spaceBetween * (rows - 1))
}

@Stable
fun LazyListState.isScrolledToTheEnd(): Boolean {
    val lastItem = layoutInfo.visibleItemsInfo.lastOrNull()
    return lastItem == null || lastItem.size + lastItem.offset <= layoutInfo.viewportEndOffset
}

@Stable
tailrec suspend fun LazyListState.animateScroll(
    delay: Long
) {
    delay(
        timeMillis = delay
    )

    if (this.isScrollInProgress) {
        this.animateScroll(
            delay = delay
        )

        return
    }

    var currentIndex = this.firstVisibleItemIndex

    if (this.isScrolledToTheEnd()) {
        currentIndex = 0
    } else currentIndex++


    this.animateScrollToItem(
        index = currentIndex
    )

    this.animateScroll(
        delay = delay
    )
}

@Stable
fun ContentDrawScope.drawFadedEdge(
    edgeWidth: Dp,
    startEdge: Boolean
) {
    val edgeWidthPx = edgeWidth.toPx()
    drawRect(
        topLeft = Offset(if (startEdge) 0f else size.width - edgeWidthPx, 0f),
        size = Size(
            width = edgeWidthPx,
            height = size.height
        ),
        brush = Brush.horizontalGradient(
            colors = listOf(
                Color.Transparent,
                Color.Black
            ),
            startX = if (startEdge) 0f else size.width,
            endX = if (startEdge) edgeWidthPx else size.width - edgeWidthPx
        ),
        blendMode = BlendMode.DstIn
    )
}

@Stable
@Composable
fun RightToLeftLayout(
    content: @Composable () -> Unit
) = CompositionLocalProvider(
    value = LocalLayoutDirection provides LayoutDirection.Rtl,
    content = content
)

@Stable
@Composable
fun LeftToRightLayout(
    content: @Composable () -> Unit
) = CompositionLocalProvider(
    value = LocalLayoutDirection provides LayoutDirection.Ltr,
    content = content
)

@Stable
@Composable
fun TextStyle.toTypeFace(
    resolver: FontFamily.Resolver
): Typeface = remember(
    this,
    resolver
) {
    resolver.resolve(
        fontFamily = this.fontFamily,
        fontWeight = this.fontWeight ?: FontWeight.Normal,
        fontStyle = this.fontStyle ?: FontStyle.Normal,
        fontSynthesis = this.fontSynthesis ?: FontSynthesis.All,
    )
}.value as Typeface

@Composable
fun Dp.toPixel(): Float {
    val density = LocalDensity.current

    return with(
        density
    ) {
        this@toPixel.toPx()
    }
}