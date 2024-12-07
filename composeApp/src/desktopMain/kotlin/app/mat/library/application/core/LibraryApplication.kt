package app.mat.library.application.core

import androidx.compose.ui.window.application
import app.mat.library.feature.core.presentation.window.application.ApplicationRootWindow

fun main() {
    application(
        exitProcessOnExit = true
    ) {
        ApplicationRootWindow()
    }
}