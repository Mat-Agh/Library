package app.mat.library.feature.core.presentation.type

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed interface UiText {
    data class StaticString(
        val value: String
    ) : UiText

    class Resource(
        val stringResource: StringResource,
        val formatArgs: Array<Any> = arrayOf()
    ) : UiText

    @Composable
    fun asString(): String {
        return when (this) {
            is StaticString -> value

            is Resource -> stringResource(
                resource = stringResource,
                formatArgs = formatArgs
            )
        }
    }
}