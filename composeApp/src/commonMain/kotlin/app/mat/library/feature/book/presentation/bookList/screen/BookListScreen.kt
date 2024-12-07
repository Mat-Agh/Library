package app.mat.library.feature.book.presentation.bookList.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import app.mat.library.feature.book.presentation.bookList.action.BookListScreenAction
import app.mat.library.feature.book.presentation.bookList.state.BookListScreenState
import app.mat.library.feature.book.presentation.graph.BookGraph
import app.mat.library.feature.book.presentation.shared.BookViewModel
import app.mat.library.feature.core.presentation.component.list.ListTypeOneComponent
import app.mat.library.feature.core.presentation.component.search.SearchBarComponent
import app.mat.library.feature.core.presentation.theme.resource.DarkBlue
import app.mat.library.feature.core.presentation.theme.resource.DesertWhite
import app.mat.library.feature.core.presentation.theme.resource.SandYellow
import library.composeapp.generated.resources.Res
import library.composeapp.generated.resources.message_favorite_books_empty
import library.composeapp.generated.resources.message_search_result_empty
import library.composeapp.generated.resources.title_favorites
import library.composeapp.generated.resources.title_search_results
import org.jetbrains.compose.resources.stringResource

@Composable
fun BookListScreenRoot(
    navHostControllerProvider: () -> NavHostController,
    viewModel: BookListScreenViewModel,
    bookViewModel: BookViewModel
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        bookViewModel.onBookSelected(
            bookState = null
        )
    }

    BookListScreen(
        stateProvider = {
            screenState
        },
        onAction = { action ->
            when (action) {
                is BookListScreenAction.OnBookClicked -> {
                    val bookState = action.bookState

                    bookViewModel.onBookSelected(
                        bookState = bookState
                    )

                    navHostControllerProvider().navigate(
                        route = BookGraph.BookDetailScreen(
                            id = bookState.id
                        )
                    )
                }

                else -> Unit
            }

            viewModel.onAction(
                action = action
            )
        }
    )
}

@Composable
fun BookListScreen(
    stateProvider: () -> BookListScreenState,
    onAction: (BookListScreenAction) -> Unit
) {
    val keyboardController by rememberUpdatedState(
        newValue = LocalSoftwareKeyboardController.current
    )

    val pagerState = rememberPagerState {
        2
    }

    val searchResultLazyListState = rememberLazyListState()

    val favoritesLazyListState = rememberLazyListState()

    LaunchedEffect(stateProvider().searchResultList) {
        if (stateProvider().searchResultList.isNotEmpty()) {
            searchResultLazyListState.animateScrollToItem(
                index = 0
            )
        }
    }

    LaunchedEffect(stateProvider().favoriteBookList) {
        if (stateProvider().favoriteBookList.isNotEmpty()) {
            favoritesLazyListState.animateScrollToItem(
                index = 0
            )
        }
    }

    LaunchedEffect(stateProvider().selectedTabIndex) {
        pagerState.animateScrollToPage(
            page = stateProvider().selectedTabIndex
        )
    }

    LaunchedEffect(pagerState.currentPage) {
        onAction(
            BookListScreenAction.OnTabSelected(
                pagerState.currentPage
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = DarkBlue
            )
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBarComponent(
            searchQuery = stateProvider().query,
            onSearchQueryChanged = {
                onAction(
                    BookListScreenAction.OnSearchQueryChanged(
                        searchQuery = it
                    )
                )
            },
            onKeyboardSearchClicked = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(
                    max = 400.dp
                )
                .fillMaxWidth()
                .padding(
                    all = 16.dp
                )
        )

        Surface(
            modifier = Modifier
                .weight(
                    weight = 1f
                )
                .fillMaxWidth(),
            color = DesertWhite,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabRow(
                    selectedTabIndex = stateProvider().selectedTabIndex,
                    containerColor = DesertWhite,
                    contentColor = SandYellow,
                    divider = {

                    },
                    indicator = { tabPositionList ->
                        TabRowDefaults.PrimaryIndicator(
                            width = 80.dp,
                            height = 5.dp,
                            shape = RoundedCornerShape(
                                corner = CornerSize(
                                    size = 12.dp
                                )
                            ),
                            color = SandYellow,
                            modifier = Modifier
                                .tabIndicatorOffset(
                                    currentTabPosition = tabPositionList[stateProvider().selectedTabIndex]
                                )
                        )
                    },
                    modifier = Modifier
                        .padding(
                            vertical = 12.dp
                        )
                        .widthIn(
                            max = 700.dp
                        )
                        .fillMaxWidth()
                ) {
                    Tab(
                        selected = stateProvider().selectedTabIndex == 0,
                        onClick = {
                            onAction(
                                BookListScreenAction.OnTabSelected(
                                    selectedTabIndex = 0
                                )
                            )
                        },
                        modifier = Modifier
                            .weight(
                                weight = 1f
                            ),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(
                            alpha = 0.5f
                        ),
                        text = {
                            Text(
                                text = stringResource(
                                    resource = Res.string.title_search_results
                                ),
                                modifier = Modifier
                                    .padding(
                                        vertical = 12.dp
                                    )
                            )
                        }
                    )

                    Tab(
                        selected = stateProvider().selectedTabIndex == 1,
                        onClick = {
                            onAction(
                                BookListScreenAction.OnTabSelected(
                                    selectedTabIndex = 1
                                )
                            )
                        },
                        modifier = Modifier
                            .weight(
                                weight = 1f
                            ),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(
                            alpha = 0.5f
                        ),
                        text = {
                            Text(
                                text = stringResource(
                                    resource = Res.string.title_favorites
                                ),
                                modifier = Modifier
                                    .padding(
                                        vertical = 12.dp
                                    )
                            )
                        }
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(
                            height = 4.dp
                        )
                )

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(
                            weight = 1f
                        )
                ) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (index) {
                            0 -> {
                                if (stateProvider().isLoading) {
                                    CircularProgressIndicator()
                                } else {
                                    when {
                                        stateProvider().errorMessage != null -> {
                                            Text(
                                                modifier = Modifier
                                                    .padding(
                                                        all = 24.dp
                                                    ),
                                                text = stateProvider().errorMessage!!.asString(),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }

                                        stateProvider().searchResultList.isEmpty() -> {
                                            Text(
                                                modifier = Modifier
                                                    .padding(
                                                        all = 24.dp
                                                    ),
                                                text = stringResource(
                                                    resource = Res.string.message_search_result_empty
                                                ),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }

                                        else -> {
                                            ListTypeOneComponent(
                                                bookStateListProvider = {
                                                    stateProvider().searchResultList
                                                },
                                                onBookClicked = { bookState ->
                                                    onAction(
                                                        BookListScreenAction.OnBookClicked(
                                                            bookState = bookState
                                                        )
                                                    )
                                                },
                                                modifier = Modifier
                                                    .fillMaxSize(),
                                                scrollStateProvider = {
                                                    searchResultLazyListState
                                                }
                                            )
                                        }
                                    }
                                }
                            }

                            1 -> {
                                if (stateProvider().favoriteBookList.isEmpty()) {
                                    Text(
                                        modifier = Modifier
                                            .padding(
                                                all = 24.dp
                                            ),
                                        text = stringResource(
                                            resource = Res.string.message_favorite_books_empty
                                        ),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                } else {
                                    ListTypeOneComponent(
                                        bookStateListProvider = {
                                            stateProvider().favoriteBookList
                                        },
                                        onBookClicked = { bookState ->
                                            onAction(
                                                BookListScreenAction.OnBookClicked(
                                                    bookState = bookState
                                                )
                                            )
                                        },
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        scrollStateProvider = {
                                            favoritesLazyListState
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}