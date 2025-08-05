package com.example.bingo.domain

import com.example.bingo.data.models.AddAddressResponse
import com.example.bingo.data.models.ApiResult
import com.example.bingo.repository.AddressRepository
import kotlinx.coroutines.flow.Flow

/**
 * Domain layer manager for address operations
 * Provides business logic and simplified interface for address management
 */
class AddressManager {
    
    private val repository = AddressRepository()

    /**
     * Example usage methods for common scenarios
     */
    
    /**
     * Add home address
     */
    fun addHomeAddress(
        address: Map<String, String>
    ): Flow<ApiResult<AddAddressResponse>> {
        return repository.addAddress(address)
    }
    
    /**
     * Clean up resources
     */
    fun cleanup() {
        repository.cleanup()
    }
}
