package app.mat.library.feature.core.presentation.parameter

data object AnimationParameter {
    data object Basic {
        const val DELAY_DURATION = 100
        const val LONG_DELAY_DURATION = 600

        const val REVERSIBLE_DURATION = 400

        const val SIZE_CHANGE_DURATION = 400
        const val COLOR_CHANGE_DURATION = 400
        const val LONG_COLOR_CHANGE_DURATION = 800
        const val SCROLL_POSITION_CHANGE_DURATION = 400
        const val ICON_CHANGE_DURATION = 400
        const val CONTENT_CHANGE_DURATION = 400

        const val CONTENT_SLIDE_IN_DURATION_FROM_TOP = 400
        const val CONTENT_SLIDE_OUT_DURATION_FROM_TOP = 400

        const val CONTENT_SLIDE_IN_DURATION_FROM_BOTTOM = 400
        const val CONTENT_SLIDE_OUT_DURATION_FROM_BOTTOM = 400

        const val FADE_IN_DURATION = 400
        const val FADE_OUT_DURATION = 400

        const val LONG_FADE_IN_DURATION = 800
        const val LONG_FADE_OUT_DURATION = 800
    }
}