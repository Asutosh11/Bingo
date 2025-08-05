package com.example.bingo.network

import com.example.bingo.data.models.AddAddressRequest
import com.example.bingo.data.models.AddAddressResponse
import com.example.bingo.data.models.ApiResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * API service for address-related operations
 */
class AddressApiService(private val httpClient: HttpClient) {
    
    /**
     * Add address to the server
     * @param addressData Map containing address data (flexible to accept any key-value pairs)
     * @return Flow<ApiResult<AddAddressResponse>>
     */
    fun addAddress(addressData: Map<String, String>): Flow<ApiResult<AddAddressResponse>> = flow {
        try {
            emit(ApiResult.Loading)
            
            val request = AddAddressRequest(data = addressData)
            
            val response = httpClient.post("${ApiConfig.BASE_URL}${ApiConfig.ADD_ADDRESS_ENDPOINT}") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            
            when (response.status) {
                HttpStatusCode.OK, HttpStatusCode.Created -> {
                    val responseBody = response.body<AddAddressResponse>()
                    emit(ApiResult.Success(responseBody))
                }
                else -> {
                    emit(ApiResult.Error("Failed to add address: ${response.status.description}", response.status.value))
                }
            }
            
        } catch (e: Exception) {
            emit(ApiResult.Error("Network error: ${e.message}"))
        }
    }
    
    /**
     * Add address with raw JSON data (alternative method for maximum flexibility)
     * @param jsonData Raw JSON string
     * @return Flow<ApiResult<AddAddressResponse>>
     */
    fun addAddressRaw(jsonData: String): Flow<ApiResult<AddAddressResponse>> = flow {
        try {
            emit(ApiResult.Loading)
            
            val response = httpClient.post("${ApiConfig.BASE_URL}${ApiConfig.ADD_ADDRESS_ENDPOINT}") {
                contentType(ContentType.Application.Json)
                setBody(jsonData)
            }
            
            when (response.status) {
                HttpStatusCode.OK, HttpStatusCode.Created -> {
                    val responseBody = response.body<AddAddressResponse>()
                    emit(ApiResult.Success(responseBody))
                }
                else -> {
                    emit(ApiResult.Error("Failed to add address: ${response.status.description}", response.status.value))
                }
            }
            
        } catch (e: Exception) {
            emit(ApiResult.Error("Network error: ${e.message}"))
        }
    }
}
