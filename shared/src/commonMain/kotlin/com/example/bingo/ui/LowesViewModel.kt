package com.example.bingo.ui

import com.example.bingo.data.models.ApiResult
import com.example.bingo.repository.AddressRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * UI State for Lowe's Address Screen
 */
data class LowesUiState(
    val isLoading: Boolean = false,
    val addresses: List<SavedAddress> = emptyList(),
    val error: String? = null,
    val successMessage: String? = null,
    val currentScreen: LowesScreen = LowesScreen.Home
)

/**
 * Saved address model for UI
 */
data class SavedAddress(
    val id: String,
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val isDefault: Boolean = false
)

/**
 * Screen navigation states
 */
enum class LowesScreen {
    Home,
    AddAddress,
    AddressList
}

/**
 * ViewModel for Lowe's Address Management
 */
class LowesAddressViewModel {
    
    private val repository = AddressRepository()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    
    private val _uiState = MutableStateFlow(LowesUiState())
    val uiState: StateFlow<LowesUiState> = _uiState.asStateFlow()
    
    // In-memory address storage (would be persisted in real app)
    private val savedAddresses = mutableListOf<SavedAddress>()
    private var addressIdCounter = 0
    
    fun navigateTo(screen: LowesScreen) {
        _uiState.value = _uiState.value.copy(
            currentScreen = screen,
            error = null,
            successMessage = null
        )
    }
    
    fun addAddress(
        street: String,
        city: String,
        state: String,
        zipCode: String,
        isDefault: Boolean = false
    ) {
        scope.launch {
            val addressData = mapOf(
                "type" to "delivery",
                "street" to street,
                "city" to city,
                "state" to state,
                "zipCode" to zipCode,
                "country" to "USA"
            )
            
            repository.addAddress(addressData).collect { result ->
                when (result) {
                    is ApiResult.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }
                    is ApiResult.Success -> {
                        // Add to local list
                        val newAddress = SavedAddress(
                            id = (++addressIdCounter).toString(),
                            street = street,
                            city = city,
                            state = state,
                            zipCode = zipCode,
                            isDefault = isDefault || savedAddresses.isEmpty()
                        )
                        
                        if (isDefault) {
                            for (i in savedAddresses.indices) {
                                savedAddresses[i] = savedAddresses[i].copy(isDefault = false)
                            }
                        }
                        savedAddresses.add(newAddress)
                        
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            addresses = savedAddresses.toList(),
                            successMessage = "Address added successfully!",
                            currentScreen = LowesScreen.AddressList
                        )
                    }
                    is ApiResult.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
    
    fun deleteAddress(addressId: String) {
        savedAddresses.removeAll { it.id == addressId }
        _uiState.value = _uiState.value.copy(
            addresses = savedAddresses.toList(),
            successMessage = "Address deleted"
        )
    }
    
    fun setDefaultAddress(addressId: String) {
        for (i in savedAddresses.indices) {
            savedAddresses[i] = savedAddresses[i].copy(isDefault = savedAddresses[i].id == addressId)
        }
        _uiState.value = _uiState.value.copy(
            addresses = savedAddresses.toList()
        )
    }
    
    fun clearMessages() {
        _uiState.value = _uiState.value.copy(
            error = null,
            successMessage = null
        )
    }
    
    fun cleanup() {
        repository.cleanup()
    }
}
