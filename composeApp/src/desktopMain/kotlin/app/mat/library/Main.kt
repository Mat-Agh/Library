package app.mat.library

import androidx.compose.ui.window.application
import app.mat.library.feature.core.presentation.window.application.ApplicationRootWindow

fun main() {
    application(
        exitProcessOnExit = true
    ) {
        ApplicationRootWindow()
    }
}