package com.example.bingo.data.models

import kotlinx.serialization.Serializable

/**
 * Request model for adding address
 * Since the API accepts "Any" type, we'll use a flexible approach with Map<String, Any?>
 */
@Serializable
data class AddAddressRequest(
    val data: Map<String, String> = emptyMap()
)

/**
 * Response model for add address API
 */
@Serializable
data class AddAddressResponse(
    val id: Int? = null,
    val status: String? = null
)

/**
 * Generic API result wrapper
 */
sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val message: String, val code: Int? = null) : ApiResult<Nothing>()
    data object Loading : ApiResult<Nothing>()
}
