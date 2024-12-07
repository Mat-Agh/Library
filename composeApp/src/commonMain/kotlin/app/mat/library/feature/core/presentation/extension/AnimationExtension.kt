package app.mat.library.feature.core.presentation.extension

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import app.mat.library.feature.core.presentation.parameter.AnimationParameter
import kotlin.math.roundToInt

//region List Animations
fun <T> expandableListItemSpec(): FiniteAnimationSpec<T> = spring(
    dampingRatio = Spring.DampingRatioMediumBouncy,
    stiffness = Spring.StiffnessLow
)

fun <T> listScrollAnimationSpec(
    durationMillis: Int = AnimationParameter.Basic.SCROLL_POSITION_CHANGE_DURATION
): FiniteAnimationSpec<T> = tween(
    durationMillis = durationMillis,
    easing = EaseOutExpo,
)
//endregion List Animations

//region Content Animations
fun <T> sizeChangeAnimationSpec(
    durationMillis: Int = AnimationParameter.Basic.SIZE_CHANGE_DURATION,
    delayMillis: Int = 0
): FiniteAnimationSpec<T> = tween(
    durationMillis = durationMillis,
    delayMillis = delayMillis,
    easing = EaseOutQuart
)

fun <T> colorChangeAnimationSpec(
    durationMillis: Int = AnimationParameter.Basic.COLOR_CHANGE_DURATION
): FiniteAnimationSpec<T> = tween(
    durationMillis = durationMillis,
    easing = EaseOutExpo,
)

fun <T> iconChangeAnimationSpec(
    durationMillis: Int = AnimationParameter.Basic.ICON_CHANGE_DURATION
): FiniteAnimationSpec<T> = tween(
    durationMillis = durationMillis,
    easing = EaseOutExpo
)

fun <T> contentPlacementChangeAnimationSpec(
    durationMillis: Int = AnimationParameter.Basic.CONTENT_CHANGE_DURATION
): FiniteAnimationSpec<T> = tween(
    durationMillis = durationMillis,
    easing = EaseOutExpo,
)
//endregion Content Animations

//region Fade Animations
fun contentFadeInAnimation(
    durationMillis: Int = AnimationParameter.Basic.FADE_IN_DURATION,
    delayMillis: Int = 0
): EnterTransition = fadeIn(
    animationSpec = tween(
        delayMillis = delayMillis,
        durationMillis = durationMillis,
        easing = EaseOutExpo
    ),
    initialAlpha = 0f
)

fun contentFadeOutAnimation(
    durationMillis: Int = AnimationParameter.Basic.FADE_OUT_DURATION,
    delayMillis: Int = 0
): ExitTransition = fadeOut(
    animationSpec = tween(
        delayMillis = delayMillis,
        durationMillis = durationMillis,
        easing = EaseOutExpo
    ),
    targetAlpha = 0f
)

fun contentLongFadeInAnimation(
    durationMillis: Int = AnimationParameter.Basic.LONG_FADE_IN_DURATION,
    delayMillis: Int = 0
): EnterTransition = fadeIn(
    animationSpec = tween(
        durationMillis = durationMillis,
        delayMillis = delayMillis,
        easing = EaseOutExpo
    ),
    initialAlpha = 0f
)

fun contentLongFadeOutAnimation(
    durationMillis: Int = AnimationParameter.Basic.LONG_FADE_OUT_DURATION,
    delayMillis: Int = 0
): ExitTransition = fadeOut(
    animationSpec = tween(
        durationMillis = durationMillis,
        delayMillis = delayMillis,
        easing = EaseOutExpo
    ),
    targetAlpha = 0f
)

fun <T> contentFadeTransitionAnimation(
    enterDurationMillis: Int = AnimationParameter.Basic.FADE_IN_DURATION,
    exitDurationMillis: Int = AnimationParameter.Basic.FADE_OUT_DURATION,
    enterDelayMillis: Int = 0,
    exitDelayMillis: Int = 0
): AnimatedContentTransitionScope<T>.() -> ContentTransform = {
    contentFadeInAnimation(
        durationMillis = enterDurationMillis,
        delayMillis = enterDelayMillis
    ).togetherWith(
        exit = contentFadeOutAnimation(
            durationMillis = exitDurationMillis,
            delayMillis = exitDelayMillis
        )
    )
}

fun <T> contentLongFadeTransitionAnimation(
    enterDurationMillis: Int = AnimationParameter.Basic.LONG_FADE_IN_DURATION,
    exitDurationMillis: Int = AnimationParameter.Basic.LONG_FADE_OUT_DURATION,
    enterDelayMillis: Int = 0,
    exitDelayMillis: Int = 0
): AnimatedContentTransitionScope<T>.() -> ContentTransform = {
    contentLongFadeInAnimation(
        durationMillis = enterDurationMillis,
        delayMillis = enterDelayMillis
    ).togetherWith(
        exit = contentLongFadeOutAnimation(
            durationMillis = exitDurationMillis,
            delayMillis = exitDelayMillis
        )
    )
}
//endregion Fade Animations

//region Slide Animations
fun contentSlideInVerticallyFromTopAnimation(
    durationMillis: Int = AnimationParameter.Basic.CONTENT_SLIDE_IN_DURATION_FROM_TOP,
    delayMillis: Int = 0,
    targetOffsetY: Float = 1f
): EnterTransition = slideInVertically(
    animationSpec = tween(
        durationMillis = durationMillis,
        delayMillis = delayMillis,
        easing = EaseOutQuart
    ),
    initialOffsetY = {
        -1 * (it * targetOffsetY).roundToInt()
    }
)

fun contentSlideOutVerticallyToTopAnimation(
    durationMillis: Int = AnimationParameter.Basic.CONTENT_SLIDE_OUT_DURATION_FROM_TOP,
    delayMillis: Int = 0,
    targetOffsetY: Float = 1f
): ExitTransition = slideOutVertically(
    animationSpec = tween(
        durationMillis = durationMillis,
        delayMillis = delayMillis,
        easing = EaseOutQuart
    ),
    targetOffsetY = {
        -1 * (it * targetOffsetY).roundToInt()
    }
)

fun contentSlideInVerticallyFromBottomAnimation(
    durationMillis: Int = AnimationParameter.Basic.CONTENT_SLIDE_IN_DURATION_FROM_BOTTOM,
    delayMillis: Int = 0,
    targetOffsetY: Float = 1f
): EnterTransition = contentFadeInAnimation(
    durationMillis = durationMillis,
    delayMillis = delayMillis
) + slideInVertically(
    animationSpec = tween(
        durationMillis = durationMillis,
        delayMillis = delayMillis,
        easing = EaseOutQuart
    ),
    initialOffsetY = {
        (it * targetOffsetY).roundToInt()
    }
)

fun contentSlideOutVerticallyToBottomAnimation(
    durationMillis: Int = AnimationParameter.Basic.CONTENT_SLIDE_OUT_DURATION_FROM_BOTTOM,
    delayMillis: Int = 0,
    targetOffsetY: Float = 1f
): ExitTransition = contentFadeOutAnimation(
    durationMillis = durationMillis,
    delayMillis = delayMillis,
) + slideOutVertically(
    animationSpec = tween(
        durationMillis = durationMillis,
        delayMillis = delayMillis,
        easing = EaseOutQuart
    ),
    targetOffsetY = {
        (it * targetOffsetY).roundToInt()
    }
)
//endregion Slide Animations