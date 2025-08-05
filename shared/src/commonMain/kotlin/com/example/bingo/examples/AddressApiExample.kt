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
     * Example 3: Add custom address with any fields
     */
    suspend fun addCustomAddressExample() {
        val customData = mapOf(
            "name" to "John Doe",
            "address_line_1" to "789 Custom Street",
            "address_line_2" to "Apt 4B",
            "city" to "Boston",
            "state" to "MA",
            "postal_code" to "02101",
            "phone" to "+1-555-0123",
            "email" to "john@example.com"
        )
        
        addressManager.addCustomAddress(customData).collect { result ->
            when (result) {
                is ApiResult.Loading -> {
                    println("Adding custom address...")
                }
                is ApiResult.Success -> {
                    println("Custom address added: ${result.data.status}")
                }
                is ApiResult.Error -> {
                    println("Error with custom address: ${result.message}")
                }
            }
        }
    }
    
    /**
     * Example 4: Add address from JSON string
     */
    suspend fun addAddressFromJsonExample() {
        val jsonData = """
            {
                "customer_id": "12345",
                "delivery_address": {
                    "street": "999 JSON Lane",
                    "city": "Seattle",
                    "state": "WA",
                    "zip": "98101"
                },
                "special_instructions": "Leave at door"
            }
        """.trimIndent()
        
        addressManager.addAddressFromJson(jsonData).collect { result ->
            when (result) {
                is ApiResult.Loading -> {
                    println("Adding address from JSON...")
                }
                is ApiResult.Success -> {
                    println("JSON address added: ${result.data.status}")
                }
                is ApiResult.Error -> {
                    println("JSON address error: ${result.message}")
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
