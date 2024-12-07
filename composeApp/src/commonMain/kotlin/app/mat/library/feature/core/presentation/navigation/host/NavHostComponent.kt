package app.mat.library.feature.core.presentation.navigation.host

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import app.mat.library.feature.book.presentation.bookDetail.action.BookDetailScreenAction
import app.mat.library.feature.book.presentation.bookDetail.screen.BookDetailScreenRoot
import app.mat.library.feature.book.presentation.bookDetail.screen.BookDetailScreenViewModel
import app.mat.library.feature.book.presentation.bookList.screen.BookListScreenRoot
import app.mat.library.feature.book.presentation.bookList.screen.BookListScreenViewModel
import app.mat.library.feature.book.presentation.graph.BookGraph
import app.mat.library.feature.book.presentation.shared.BookViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavHostComponent(
    navHostControllerProvider: () -> NavHostController,
    startDestinationProvider: () -> BookGraph
) {
    NavHost(
        navController = navHostControllerProvider(),
        startDestination = startDestinationProvider()
    ) {
        navigation<BookGraph.Root>(
            startDestination = BookGraph.BookListScreen,
        ) {
            composable<BookGraph.BookListScreen> { backStackEntry ->
                val viewModel: BookListScreenViewModel = koinViewModel()

                val bookViewModel: BookViewModel = backStackEntry.getSharedKoinViewModel(
                    navHostController = navHostControllerProvider()
                )

                BookListScreenRoot(
                    navHostControllerProvider = navHostControllerProvider,
                    viewModel = viewModel,
                    bookViewModel = bookViewModel
                )
            }

            composable<BookGraph.BookDetailScreen> { backStackEntry ->
                val bookViewModel: BookViewModel = backStackEntry.getSharedKoinViewModel(
                    navHostController = navHostControllerProvider()
                )

                val viewModel: BookDetailScreenViewModel = koinViewModel()

                val selectedBookState by bookViewModel.selectedBookState.collectAsStateWithLifecycle()

                LaunchedEffect(
                    selectedBookState
                ) {
                    selectedBookState?.let {
                        viewModel.onAction(
                            action = BookDetailScreenAction.OnSelectedBookChanged(
                                bookState = it
                            )
                        )
                    }
                }

                BookDetailScreenRoot(
                    viewModel = viewModel,
                    onBackClicked = {
                        navHostControllerProvider().navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.getSharedKoinViewModel(
    navHostController: NavHostController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()

    val parentEntry = remember(
        this
    ) {
        navHostController.getBackStackEntry(
            route = navGraphRoute
        )
    }

    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}