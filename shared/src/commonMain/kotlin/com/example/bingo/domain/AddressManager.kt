package com.example.bingo.domain

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
                    is ApiResult.Loading -> onLoading()
                    is ApiResult.Success -> onSuccess(result.data)
                    is ApiResult.Error -> onError(result.message)
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
