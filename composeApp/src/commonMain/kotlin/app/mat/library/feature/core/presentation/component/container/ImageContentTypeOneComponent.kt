package app.mat.library.feature.core.presentation.component.container

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import app.mat.library.feature.core.presentation.component.progress.PulseProgress
import app.mat.library.feature.core.presentation.theme.resource.DarkBlue
import app.mat.library.feature.core.presentation.theme.resource.DesertWhite
import app.mat.library.feature.core.presentation.theme.resource.SandYellow
import coil3.compose.rememberAsyncImagePainter
import library.composeapp.generated.resources.Res
import library.composeapp.generated.resources.book_error
import library.composeapp.generated.resources.title_book_cover
import library.composeapp.generated.resources.ui_hint_add_favorite
import library.composeapp.generated.resources.ui_hint_go_back
import library.composeapp.generated.resources.ui_hint_remove_favorite
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ImageContentTypeOneComponent(
    modifier: Modifier = Modifier,
    imageUrlProvider: () -> String?,
    favoriteStatusProvider: () -> Boolean,
    onFavoriteClicked: () -> Unit,
    onBackClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(
            value = null
        )
    }

    var cardElevationState by remember {
        mutableStateOf(
            value = 0.dp
        )
    }

    val cardElevationAnimation by animateDpAsState(
        targetValue = cardElevationState,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 400
        )
    )

    val painter = rememberAsyncImagePainter(
        model = imageUrlProvider(),
        onSuccess = {
            val size = it.painter.intrinsicSize

            imageLoadResult = if (size.width > 1 && size.height > 1) {
                Result.success(
                    value = it.painter
                ).also {
                    cardElevationState = 20.dp
                }
            } else {
                Result.failure<Painter>(
                    exception = Exception("Invalid intrinsic size: $size")
                ).also {
                    cardElevationState = 0.dp
                }
            }
        },
        onError = {
            it.result.throwable.printStackTrace()

            cardElevationState = 0.dp
        }
    )

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(
                        weight = 0.3f
                    )
                    .fillMaxWidth()
                    .background(
                        color = DarkBlue
                    )
            ) {
                Image(
                    painter = painter,
                    contentDescription = stringResource(
                        resource = Res.string.title_book_cover
                    ),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(
                            radius = 20.dp
                        )
                )
            }

            Box(
                modifier = Modifier
                    .weight(
                        weight = 0.7f
                    )
                    .fillMaxWidth()
                    .background(
                        color = DesertWhite
                    )
            )
        }

        IconButton(
            onClick = onBackClicked,
            modifier = Modifier
                .align(
                    alignment = Alignment.TopStart
                )
                .padding(
                    top = 16.dp,
                    start = 16.dp
                )
                .statusBarsPadding()
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(
                    resource = Res.string.ui_hint_go_back
                ),
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxHeight(
                        fraction = 0.15f
                    )
            )

            Box(
                modifier = Modifier
                    .height(
                        height = 250.dp
                    )
                    .aspectRatio(
                        ratio = 2 / 3f
                    )
                    .shadow(
                        shape = RoundedCornerShape(
                            size = 8.dp
                        ),
                        clip = cardElevationState != 0.dp,
                        elevation = cardElevationAnimation
                    )
                    .background(
                        shape = RoundedCornerShape(
                            size = 8.dp
                        ),
                        color = Color.Transparent
                    )
            ) {
                AnimatedContent(
                    targetState = imageLoadResult,
                ) { result ->
                    when (result) {
                        null -> Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            PulseProgress(
                                modifier = Modifier
                                    .size(
                                        size = 60.dp
                                    )
                            )
                        }

                        else -> {
                            Box {
                                Image(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            color = Color.Transparent
                                        ),
                                    painter = if (result.isSuccess) {
                                        painter
                                    } else {
                                        painterResource(
                                            Res.drawable.book_error
                                        )
                                    },
                                    contentDescription = stringResource(
                                        resource = Res.string.title_book_cover
                                    ),
                                    contentScale = if (result.isSuccess) {
                                        ContentScale.Crop
                                    } else {
                                        ContentScale.Fit
                                    }
                                )

                                IconButton(
                                    modifier = Modifier
                                        .align(
                                            alignment = Alignment.BottomEnd
                                        )
                                        .background(
                                            brush = Brush.radialGradient(
                                                colors = listOf(
                                                    SandYellow,
                                                    Color.Transparent
                                                ),
                                                radius = 70f
                                            )
                                        ),
                                    onClick = onFavoriteClicked
                                ) {
                                    Icon(
                                        imageVector = if (favoriteStatusProvider()) {
                                            Icons.Filled.Favorite
                                        } else {
                                            Icons.Default.FavoriteBorder
                                        },
                                        tint = Color.Red,
                                        contentDescription = if (favoriteStatusProvider()) {
                                            stringResource(
                                                resource = Res.string.ui_hint_remove_favorite
                                            )
                                        } else {
                                            stringResource(
                                                resource = Res.string.ui_hint_add_favorite
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            content()
        }
    }
}