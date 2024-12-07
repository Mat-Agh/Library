package app.mat.library.feature.core.presentation.window.application

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import app.mat.library.feature.core.presentation.screen.application.ApplicationRootScreen
import app.mat.library.di.initializer.initializeKoin
import app.mat.library.feature.core.presentation.type.UiText

@Composable
fun ApplicationRootWindow() {
    initializeKoin()

    ApplicationWindow(
        windowTitleProvider = {
            UiText.StaticString(
                "Hello"
            )
        },
        onCloseRequest = {
            // Tell compose to close process or hide the window
        }
    )
}

@Composable
fun ApplicationWindow(
    windowTitleProvider: () -> UiText,
    onCloseRequest: () -> Unit
) {
    Window(
        onCloseRequest = onCloseRequest,
        title = windowTitleProvider().asString()
    ) {
        ApplicationRootScreen()
    }
}