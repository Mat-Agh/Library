package app.mat.library.feature.core.domain.type

sealed interface Result<out D, out E : Error> {
    data class Success<out D>(
        val data: D
    ) : Result<D, Nothing>

    data class Error<out E : app.mat.library.feature.core.domain.type.Error>(
        val error: E
    ) : Result<Nothing, E>
}

typealias EmptyResult<E> = Result<Unit, E>

inline fun <D, E : Error, R> Result<D, E>.map(transform: (D) -> R): Result<R, E> = when (this) {
    is Result.Success -> Result.Success(
        transform(data)
    )

    is Result.Error -> Result.Error(
        error
    )
}

fun <D, E : Error> Result<D, E>.asEmptyDataResult(): EmptyResult<E> = map {}

inline fun <D, E : Error> Result<D, E>.onSuccess(action: (D) -> Unit): Result<D, E> = when (this) {
    is Result.Error -> this

    is Result.Success -> {
        action(data)
        this
    }
}

inline fun <D, E : Error> Result<D, E>.onError(action: (E) -> Unit): Result<D, E> = when (this) {
    is Result.Error -> {
        action(error)
        this
    }

    is Result.Success -> this
}