package cz.ich.core.domain.model

/**
 * Result of some operation.
 */
sealed interface Result<out D> {

    /**
     * Success [Result] with corresponding data.
     */
    data class Success<out D>(val data: D) : Result<D>

    /**
     * Error [Result] with corresponding error.
     */
    data class Error(val error: BaseError) : Result<Nothing>
}

/**
 * Map [Result] with [map] function to [Result] with another type.
 */
inline fun <T, R> Result<T>.map(map: (T) -> R): Result<R> {
    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

/**
 * Execute [action] if [Result] is [Result.Success].
 */
inline fun <T> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    return when (this) {
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }
    }
}

/**
 * Execute [action] if [Result] is [Result.Error].
 */
inline fun <T> Result<T>.onError(action: (BaseError) -> Unit): Result<T> {
    return when (this) {
        is Result.Error -> {
            action(error)
            this
        }

        is Result.Success -> this
    }
}