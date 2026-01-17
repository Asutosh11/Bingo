package com.example.bingo.domain

import com.example.bingo.BingoSDK
import com.example.bingo.data.models.AddAddressResponse
import com.example.bingo.data.models.ApiResult
import com.example.bingo.repository.AddressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Domain layer manager for address operations
 * Provides business logic and simplified interface for address management
 */
class AddressManager {
    
    private val repository = AddressRepository()
    private val scope = CoroutineScope(Dispatchers.Main)

    /**
     * Helper: validate address data
     * @param address Map containing address fields
     * @return List of validation error messages, empty if valid
     */
    fun validateAddress(address: Map<String, String>): List<String> {
        val errors = mutableListOf<String>()
        
        address.forEach { (key, value) ->
            if (value.trim().isBlank()) {
                errors.add("$key is mandatory")
            }
        }
        
        // Track validation event
        if (errors.isEmpty()) {
            BingoSDK.trackEvent("address_validation_success", mapOf(
                "field_count" to address.size
            ))
        } else {
            BingoSDK.trackEvent("address_validation_failed", mapOf(
                "error_count" to errors.size,
                "errors" to errors.joinToString(", ")
            ))
        }
        
        return errors
    }

    /**
     * API: Add home address (Flow-based for Android)
     */
    fun addHomeAddress(
        address: Map<String, String>
    ): Flow<ApiResult<AddAddressResponse>> {
        return repository.addAddress(address)
    }
    
    /**
     * API: Add home address with callback (iOS-friendly)
     * @param address Map containing address fields
     * @param onLoading Callback when request starts
     * @param onSuccess Callback when request succeeds
     * @param onError Callback when request fails
     */
    fun addHomeAddress(
        address: Map<String, String>,
        onLoading: () -> Unit,
        onSuccess: (AddAddressResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        scope.launch {
            addHomeAddress(address).collect { result ->
                when (result) {
                    is ApiResult.Loading -> {
                        BingoSDK.trackEvent("address_submission_started", mapOf(
                            "address_type" to (address["type"] ?: "unknown")
                        ))
                        onLoading()
                    }
                    is ApiResult.Success -> {
                        BingoSDK.trackEvent("address_submission_success", mapOf(
                            "address_type" to (address["type"] ?: "unknown"),
                            "response_message" to (result.data)
                        ))
                        onSuccess(result.data)
                    }
                    is ApiResult.Error -> {
                        BingoSDK.trackEvent("address_submission_failed", mapOf(
                            "address_type" to (address["type"] ?: "unknown"),
                            "error_message" to result.message
                        ))
                        onError(result.message)
                    }
                }
            }
        }
    }
    
    /**
     * Clean up resources
     */
    fun cleanup() {
        repository.cleanup()
    }
}
