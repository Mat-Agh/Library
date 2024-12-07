package app.mat.library.feature.core.presentation.component.listItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.mat.library.feature.book.presentation.state.BookState
import app.mat.library.feature.core.presentation.theme.resource.LightBlue
import app.mat.library.feature.core.presentation.theme.resource.SandYellow
import coil3.compose.rememberAsyncImagePainter
import library.composeapp.generated.resources.Res
import library.composeapp.generated.resources.book_error
import org.jetbrains.compose.resources.painterResource
import kotlin.math.round

@Composable
fun ListItemTypeOneComponent(
    bookState: BookState = BookState.default,
    onBookClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(
            size = 32.dp
        ),
        modifier = modifier
            .clickable(
                onClick = onBookClicked
            ),
        color = LightBlue.copy(
            alpha = 0.2f
        )
    ) {
        Row(
            modifier = Modifier
                .padding(
                    all = 16.dp
                )
                .fillMaxWidth()
                .height(
                    IntrinsicSize.Min
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 16.dp
            )
        ) {
            Box(
                modifier = Modifier
                    .height(
                        height = 100.dp,
                    ),
                contentAlignment = Alignment.Center
            ) {
                var imageLoadResult by remember {
                    mutableStateOf<Result<Painter>?>(
                        value = null
                    )
                }

                val painter = rememberAsyncImagePainter(
                    model = bookState.imageAddress,
                    onSuccess = {
                        imageLoadResult = if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                            Result.success(
                                it.painter
                            )
                        } else {
                            Result.failure(
                                Exception(
                                    "Invalid Image Size"
                                )
                            )
                        }
                    },
                    onError = {
                        it.result.throwable.printStackTrace()

                        imageLoadResult = Result.failure(
                            it.result.throwable
                        )
                    }
                )

                when (val result = imageLoadResult) {
                    null -> CircularProgressIndicator()
                    else -> {
                        Image(
                            painter = if (result.isSuccess) {
                                painter
                            } else {
                                painterResource(
                                    resource = Res.drawable.book_error
                                )
                            },
                            contentDescription = bookState.title,
                            contentScale = if (result.isSuccess) {
                                ContentScale.Crop
                            } else {
                                ContentScale.Fit
                            },
                            modifier = Modifier
                                .aspectRatio(
                                    ratio = 0.65f,
                                    matchHeightConstraintsFirst = true
                                )
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(
                        weight = 1f
                    ),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = bookState.title ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                bookState.authorList?.firstOrNull()?.let { author ->
                    Text(
                        text = author,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                bookState.averageRating?.let { averageRating ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${round(averageRating * 10) / 10.0}",
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = SandYellow
                        )
                    }
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier
                    .size(
                        size = 36.dp
                    )
            )
        }
    }
}