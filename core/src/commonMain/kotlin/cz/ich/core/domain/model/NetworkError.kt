package cz.ich.core.domain.model

/**
 * Error for network communication purpose.
 */
enum class NetworkError : BaseError {
    BadRequest,
    RequestTimeout,
    Unauthorized,
    NotFound,
    Conflict,
    TooManyRequests,
    NoInternet,
    PayloadTooLarge,
    ServerError,
    Serialization,
    Unknown;
}