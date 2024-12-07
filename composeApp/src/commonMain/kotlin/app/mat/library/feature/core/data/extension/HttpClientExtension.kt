package app.mat.library.feature.core.data.extension

import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.domain.type.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeApiCall(
    execute: () -> HttpResponse
): Result<T, DataError.Remote> {
    val response = try {
        execute()
    } catch (
        exception: SocketTimeoutException
    ) {
        return Result.Error(
            error = DataError.Remote.TIMEOUT
        )
    } catch (
        exception: UnresolvedAddressException
    ) {
        return Result.Error(
            error = DataError.Remote.NO_INTERNET
        )
    } catch (
        exception: Exception
    ) {
        coroutineContext.ensureActive()

        return Result.Error(
            error = DataError.Remote.UNKNOWN
        )
    }


    return responseToResult(
        response = response
    )
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, DataError.Remote> = when (response.status.value) {
    in 200..299 -> {
        try {
            Result.Success(
                data = response.body<T>()
            )
        } catch (
            exception: NoTransformationFoundException
        ) {
            Result.Error(
                error = DataError.Remote.SERIALIZATION
            )
        }
    }

    408 -> Result.Error(
        error = DataError.Remote.TIMEOUT
    )

    429 -> Result.Error(
        error = DataError.Remote.TOO_MANY_REQUESTS
    )

    in 500..599 -> Result.Error(
        error = DataError.Remote.SERVER
    )

    else -> Result.Error(
        error = DataError.Remote.UNKNOWN
    )
}