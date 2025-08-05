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
     * Add a complete address
     */
    fun addCompleteAddress(
        street: String,
        city: String,
        state: String,
        zipCode: String,
        country: String
    ): Flow<ApiResult<AddAddressResponse>> {
        return repository.addAddress(
            street = street,
            city = city,
            state = state,
            zipCode = zipCode,
            country = country
        )
    }
    
    /**
     * Add address with custom fields
     */
    fun addCustomAddress(addressData: Map<String, String>): Flow<ApiResult<AddAddressResponse>> {
        return repository.addAddress(addressData)
    }
    
    /**
     * Add address from JSON string
     */
    fun addAddressFromJson(jsonData: String): Flow<ApiResult<AddAddressResponse>> {
        return repository.addAddressRaw(jsonData)
    }
    
    /**
     * Example usage methods for common scenarios
     */
    
    /**
     * Add home address
     */
    fun addHomeAddress(
        street: String,
        city: String,
        state: String,
        zipCode: String,
        country: String = "USA"
    ): Flow<ApiResult<AddAddressResponse>> {
        val addressData = mapOf(
            "type" to "home",
            "street" to street,
            "city" to city,
            "state" to state,
            "zipCode" to zipCode,
            "country" to country
        )
        return repository.addAddress(addressData)
    }
    
    /**
     * Add work address
     */
    fun addWorkAddress(
        companyName: String,
        street: String,
        city: String,
        state: String,
        zipCode: String,
        country: String = "USA"
    ): Flow<ApiResult<AddAddressResponse>> {
        val addressData = mapOf(
            "type" to "work",
            "companyName" to companyName,
            "street" to street,
            "city" to city,
            "state" to state,
            "zipCode" to zipCode,
            "country" to country
        )
        return repository.addAddress(addressData)
    }
    
    /**
     * Clean up resources
     */
    fun cleanup() {
        repository.cleanup()
    }
}
