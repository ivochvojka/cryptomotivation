package cz.ich.core.domain.model

/**
 * Error for network communication purpose.
 */
enum class NetworkError : BaseError {
    RequestTimeout,
    Unauthorized,
    Conflict,
    TooManyRequests,
    NoInternet,
    PayloadTooLarge,
    ServerError,
    Serialization,
    Unknown;
}