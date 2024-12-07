package app.mat.library.feature.core.presentation.extension

import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.presentation.type.UiText
import library.composeapp.generated.resources.Res
import library.composeapp.generated.resources.message_disk_is_full
import library.composeapp.generated.resources.message_no_internet
import library.composeapp.generated.resources.message_request_time_out
import library.composeapp.generated.resources.message_serialization
import library.composeapp.generated.resources.message_too_many_requests
import library.composeapp.generated.resources.message_unknown

fun DataError.toUiText(): UiText {
    val stringResource = when (this) {
        DataError.Local.DISK_FULL -> Res.string.message_disk_is_full
        DataError.Local.UNKNOWN -> Res.string.message_unknown
        DataError.Remote.TIMEOUT -> Res.string.message_request_time_out
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.message_too_many_requests
        DataError.Remote.NO_INTERNET -> Res.string.message_no_internet
        DataError.Remote.SERVER -> Res.string.message_unknown
        DataError.Remote.SERIALIZATION -> Res.string.message_serialization
        DataError.Remote.UNKNOWN -> Res.string.message_unknown
    }

    return UiText.Resource(
        stringResource = stringResource
    )
}