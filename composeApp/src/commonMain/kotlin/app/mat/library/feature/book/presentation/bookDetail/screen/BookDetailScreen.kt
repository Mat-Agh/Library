package app.mat.library.feature.book.presentation.bookDetail.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.mat.library.feature.book.presentation.bookDetail.action.BookDetailScreenAction
import app.mat.library.feature.book.presentation.bookDetail.state.BookDetailScreenState
import app.mat.library.feature.core.presentation.component.chip.ChipTypeOne
import app.mat.library.feature.core.presentation.component.container.TitledContent
import app.mat.library.feature.core.presentation.component.image.ImageContentTypeOneComponent
import app.mat.library.feature.core.presentation.theme.resource.SandYellow
import app.mat.library.feature.core.presentation.type.ChipSize
import library.composeapp.generated.resources.Res
import library.composeapp.generated.resources.message_description_not_available
import library.composeapp.generated.resources.title_languages
import library.composeapp.generated.resources.title_pages
import library.composeapp.generated.resources.title_rating
import library.composeapp.generated.resources.title_synopsis
import library.composeapp.generated.resources.title_title
import org.jetbrains.compose.resources.stringResource
import kotlin.math.round

@Composable
fun BookDetailScreenRoot(
    viewModel: BookDetailScreenViewModel,
    onBackClicked: () -> Unit
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    val contentScrollState = rememberScrollState()

    BookDetailScreen(
        screenState = screenState,
        contentScrollStateProvider = {
            contentScrollState
        },
        onAction = { action ->
            when (action) {
                BookDetailScreenAction.OnBackClicked -> {
                    onBackClicked()
                }

                else -> Unit
            }

            viewModel.onAction(
                action = action
            )
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BookDetailScreen(
    screenState: BookDetailScreenState,
    contentScrollStateProvider: () -> ScrollState,
    onAction: (BookDetailScreenAction) -> Unit
) {
    ImageContentTypeOneComponent(
        imageUrlProvider = {
            screenState.bookState?.imageAddress
        },
        favoriteStatusProvider = {
            screenState.isFavorite
        },
        onFavoriteClicked = {
            onAction(
                BookDetailScreenAction.OnFavoriteClicked
            )
        },
        onBackClicked = {
            onAction(
                BookDetailScreenAction.OnBackClicked
            )
        },
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (screenState.bookState != null) {
            Column(
                modifier = Modifier
                    .widthIn(
                        max = 700.dp
                    )
                    .fillMaxWidth()
                    .padding(
                        vertical = 16.dp,
                        horizontal = 24.dp
                    )
                    .verticalScroll(
                        state = contentScrollStateProvider()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = screenState.bookState.title ?: stringResource(
                        resource = Res.string.title_title
                    ),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                )

                if (!screenState.bookState.authorList.isNullOrEmpty()) {
                    Spacer(
                        modifier = Modifier
                            .height(
                                height = 8.dp
                            )
                    )

                    Text(
                        text = screenState.bookState.authorList.joinToString(
                            separator = "|"
                        ),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(
                            vertical = 8.dp
                        ),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 16.dp
                    )
                ) {
                    screenState.bookState.averageRating?.let { rating ->
                        TitledContent(
                            titleProvider = {
                                stringResource(
                                    resource = Res.string.title_rating
                                )
                            }
                        ) {
                            ChipTypeOne(
                                chipSizeProvider = {
                                    ChipSize.BIG
                                }
                            ) {
                                Text(
                                    text = "${
                                        round(
                                            x = rating * 10
                                        ) / 10.0
                                    }",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.labelLarge
                                )

                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = SandYellow
                                )
                            }
                        }
                    }

                    screenState.bookState.pageCount?.let { pageCount ->
                        TitledContent(
                            titleProvider = {
                                stringResource(
                                    resource = Res.string.title_pages
                                )
                            }
                        ) {
                            ChipTypeOne(
                                chipSizeProvider = {
                                    ChipSize.BIG
                                }
                            ) {
                                Text(
                                    text = pageCount.toString(),
                                    style = MaterialTheme.typography.labelLarge,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                if (!screenState.bookState.languageList.isNullOrEmpty()) {
                    TitledContent(
                        titleProvider = {
                            stringResource(
                                resource = Res.string.title_languages
                            )
                        },
                        modifier = Modifier
                            .padding(
                                vertical = 8.dp
                            )
                    ) {
                        FlowRow(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .wrapContentSize(
                                    Alignment.Center
                                )
                        ) {
                            screenState.bookState.languageList.forEach { language ->
                                ChipTypeOne(
                                    modifier = Modifier
                                        .padding(
                                            all = 2.dp
                                        ),
                                    chipSizeProvider = {
                                        ChipSize.SMALL
                                    }
                                ) {
                                    Text(
                                        text = language.uppercase(),
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                }
                            }
                        }
                    }
                }

                Text(
                    text = stringResource(
                        resource = Res.string.title_synopsis
                    ),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .align(
                            alignment = Alignment.Start
                        )
                        .fillMaxWidth()
                        .padding(
                            top = 24.dp,
                            bottom = 8.dp
                        )
                )

                if (screenState.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(
                        text = if (screenState.bookState.description.isNullOrBlank()) {
                            stringResource(
                                resource = Res.string.message_description_not_available
                            )
                        } else {
                            screenState.bookState.description
                        },
                        style = MaterialTheme.typography.titleLarge,
                        color = if (screenState.bookState.description.isNullOrBlank()) {
                            Color.Black.copy(
                                alpha = 0.4f
                            )
                        } else {
                            Color.Black
                        },
                        modifier = Modifier
                            .align(
                                alignment = Alignment.Start
                            )
                            .fillMaxWidth()
                            .padding(
                                vertical = 8.dp
                            )
                    )
                }
            }
        }
    }
}