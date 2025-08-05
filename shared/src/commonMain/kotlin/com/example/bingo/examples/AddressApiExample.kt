package com.example.bingo.examples

import com.example.bingo.data.models.ApiResult
import com.example.bingo.domain.AddressManager

/**
 * Example usage of the Address API
 * This class demonstrates how to use the AddressManager to interact with the API
 */
class AddressApiExample {
    
    private val addressManager = AddressManager()

    /**
     * Example 1: Add a complete home address
     */
    suspend fun addHomeAddress(address: Map<String, String>) {
        addressManager.addHomeAddress(
            address
        ).collect { result ->
            when (result) {
                is ApiResult.Loading -> {
                    println("Payload: $address")
                }
                is ApiResult.Success -> {
                    println("Response: ${result.data.status}")
                }
                is ApiResult.Error -> {
                    println("Error adding address: ${result.message}")
                }
            }
        }
    }

    /**
     * Clean up resources when done
     */
    fun cleanup() {
        addressManager.cleanup()
    }
}
