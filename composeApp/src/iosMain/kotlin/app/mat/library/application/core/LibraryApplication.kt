package app.mat.library.application.core

import androidx.compose.ui.window.ComposeUIViewController
import app.mat.library.di.initializer.initializeKoin
import app.mat.library.feature.core.presentation.screen.application.ApplicationRootScreen

fun libraryViewController() = ComposeUIViewController(
    configure = {
        initializeKoin()
    }
) {
    ApplicationRootScreen()
}