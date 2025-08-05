package com.example.bingo.network

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * HTTP client configuration for the application
 */
object HttpClientFactory {
    
    fun create(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }
    }
}

/**
 * API endpoints configuration
 */
object ApiConfig {
    const val BASE_URL = "https://composekmp.free.beeceptor.com"
    const val ADD_ADDRESS_ENDPOINT = "/addaddress"
}
