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
     * Clean up resources when repository is no longer needed
     */
    fun cleanup() {
        httpClient.close()
    }
}
