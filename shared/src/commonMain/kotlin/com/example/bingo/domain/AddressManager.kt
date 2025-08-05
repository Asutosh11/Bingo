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
     * Validate address data
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
