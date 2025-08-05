package com.example.bingo.repository

import com.example.bingo.data.models.AddAddressResponse
import com.example.bingo.data.models.ApiResult
import com.example.bingo.network.AddressApiService
import com.example.bingo.network.HttpClientFactory
import kotlinx.coroutines.flow.Flow

/**
 * Repository for address-related operations
 * Provides a clean interface between the UI layer and the network layer
 */
class AddressRepository {
    
    private val httpClient = HttpClientFactory.create()
    private val apiService = AddressApiService(httpClient)
    
    /**
     * Add address with structured data
     * @param addressData Map containing address information
     * @return Flow<ApiResult<AddAddressResponse>>
     */
    fun addAddress(addressData: Map<String, String>): Flow<ApiResult<AddAddressResponse>> {
        return apiService.addAddress(addressData)
    }
    
    /**
     * Add address with raw JSON data
     * @param jsonData Raw JSON string
     * @return Flow<ApiResult<AddAddressResponse>>
     */
    fun addAddressRaw(jsonData: String): Flow<ApiResult<AddAddressResponse>> {
        return apiService.addAddressRaw(jsonData)
    }
    
    /**
     * Convenience method to add address with common fields
     * @param street Street address
     * @param city City name
     * @param state State/Province
     * @param zipCode ZIP/Postal code
     * @param country Country name
     * @return Flow<ApiResult<AddAddressResponse>>
     */
    fun addAddress(
        street: String? = null,
        city: String? = null,
        state: String? = null,
        zipCode: String? = null,
        country: String? = null
    ): Flow<ApiResult<AddAddressResponse>> {
        val addressData = mutableMapOf<String, String>()
        
        street?.let { addressData["street"] = it }
        city?.let { addressData["city"] = it }
        state?.let { addressData["state"] = it }
        zipCode?.let { addressData["zipCode"] = it }
        country?.let { addressData["country"] = it }
        
        return addAddress(addressData)
    }
    
    /**
     * Clean up resources when repository is no longer needed
     */
    fun cleanup() {
        httpClient.close()
    }
}
