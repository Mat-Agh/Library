package app.mat.library.feature.core.presentation.screen.application

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.mat.library.feature.book.presentation.graph.BookGraph
import app.mat.library.feature.core.presentation.navigation.host.NavHostComponent
import app.mat.library.feature.core.presentation.theme.LibraryRootTheme

@Composable
fun ApplicationRootScreen() {
    val navController by rememberUpdatedState(
        newValue = rememberNavController()
    )

    val startDestination by rememberUpdatedState(
        newValue = BookGraph.Root
    )

    LibraryRootTheme {
        ApplicationScreen(
            navHostControllerProvider = {
                navController
            },
            startDestinationProvider = {
                startDestination
            }
        )
    }
}

@Composable
private fun ApplicationScreen(
    navHostControllerProvider: () -> NavHostController,
    startDestinationProvider: () -> BookGraph
) {
    NavHostComponent(
        navHostControllerProvider = navHostControllerProvider,
        startDestinationProvider = startDestinationProvider
    )
}