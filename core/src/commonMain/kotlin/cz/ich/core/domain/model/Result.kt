package cz.ich.core.domain.model

sealed interface Result<out D> {
    data class Success<out D>(val data: D) : Result<D>
    data class Error(val error: BaseError) : Result<Nothing>
}

inline fun <T, R> Result<T>.map(map: (T) -> R): Result<R> {
    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

inline fun <T> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    return when (this) {
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }
    }
}

inline fun <T> Result<T>.onError(action: (BaseError) -> Unit): Result<T> {
    return when (this) {
        is Result.Error -> {
            action(error)
            this
        }

        is Result.Success -> this
    }
}

typealias EmptyResult = Result<Unit>