package com.example.bingo

import com.example.bingo.domain.AddressManager
import com.example.bingo.data.models.AddAddressResponse
import com.example.bingo.data.models.ApiResult
import com.example.bingo.analytics.AnalyticsAdapter
import kotlinx.coroutines.flow.Flow

/**
 * Main entry point for the Bingo SDK
 * Provides a simplified interface for address management functionality
 */
object BingoSDK {
    
    private val addressManager = AddressManager()
    
    /**
     * Analytics adapter instance (optional)
     * Set via initialize() method
     */
    internal var analyticsAdapter: AnalyticsAdapter? = null
        private set
    
    /**
     * Get the current SDK version
     */
    const val VERSION = "1.0.6"

    fun initialize(adapter: AnalyticsAdapter) {
        analyticsAdapter = adapter
        trackEvent("bingo_sdk_initialized", mapOf("version" to VERSION))
    }
    
    /**
     * Track an analytics event (internal helper)
     */
    internal fun trackEvent(name: String, params: Map<String, Any> = emptyMap()) {
        analyticsAdapter?.trackEvent(name, params)
    }
    
    /**
     * Validate address data before submission
     * @param address Map containing address fields (street, city, state, zipCode, country)
     * @return List of validation error messages, empty if valid
     */
    fun validateAddress(address: Map<String, String>): List<String> {
        return addressManager.validateAddress(address)
    }
    
    /**
     * Add a home address (Flow-based for Android/JVM)
     * @param address Map containing address fields
     * @return Flow of ApiResult containing the response
     */
    fun addHomeAddress(address: Map<String, String>): Flow<ApiResult<AddAddressResponse>> {
        return addressManager.addHomeAddress(address)
    }
    
    /**
     * Add a home address with callbacks (iOS-friendly)
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
        addressManager.addHomeAddress(address, onLoading, onSuccess, onError)
    }
    
    /**
     * Create a properly formatted address map
     * @param street Street address
     * @param city City name
     * @param state State or province
     * @param zipCode ZIP or postal code
     * @param country Country name
     * @return Map with proper address structure
     */
    fun createAddress(
        street: String,
        city: String,
        state: String,
        zipCode: String,
        country: String
    ): Map<String, String> {
        return mapOf(
            "type" to "home",
            "street" to street,
            "city" to city,
            "state" to state,
            "zipCode" to zipCode,
            "country" to country
        )
    }
    
    /**
     * Clean up SDK resources
     * Call this when you're done using the SDK
     */
    fun cleanup() {
        addressManager.cleanup()
    }
}
