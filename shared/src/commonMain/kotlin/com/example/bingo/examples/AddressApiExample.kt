package com.example.bingo.examples

import com.example.bingo.data.models.ApiResult
import com.example.bingo.domain.AddressManager
import kotlinx.coroutines.flow.collect

/**
 * Example usage of the Address API
 * This class demonstrates how to use the AddressManager to interact with the API
 */
class AddressApiExample {
    
    private val addressManager = AddressManager()
    
    /**
     * Example 1: Add a complete home address
     */
    suspend fun addHomeAddressExample() {
        addressManager.addHomeAddress(
            street = "123 Main Street",
            city = "New York",
            state = "NY",
            zipCode = "10001",
            country = "USA"
        ).collect { result ->
            when (result) {
                is ApiResult.Loading -> {
                    println("Adding address...")
                }
                is ApiResult.Success -> {
                    println("Address added successfully: ${result.data.status}")
                }
                is ApiResult.Error -> {
                    println("Error adding address: ${result.message}")
                }
            }
        }
    }
    
    /**
     * Example 2: Add a work address
     */
    suspend fun addWorkAddressExample() {
        addressManager.addWorkAddress(
            companyName = "Tech Corp",
            street = "456 Business Ave",
            city = "San Francisco",
            state = "CA",
            zipCode = "94105",
            country = "USA"
        ).collect { result ->
            when (result) {
                is ApiResult.Loading -> {
                    println("Adding work address...")
                }
                is ApiResult.Success -> {
                    println("Work address added: ${result.data.status}")
                }
                is ApiResult.Error -> {
                    println("Failed to add work address: ${result.message}")
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
