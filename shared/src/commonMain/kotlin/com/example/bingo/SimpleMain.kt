package com.example.bingo

import com.example.bingo.examples.AddressApiExample
import kotlinx.coroutines.runBlocking

/**
 * Simple main function to test the Address API
 * This will be used for JVM execution
 */
fun main() = runBlocking {
    println("🚀 Running Address API Example...")
    val example = AddressApiExample()

    try {
        println("\n📍 Testing Home Address API...")
        val address = mapOf<String, String>(
            "type" to "home",
            "street" to "123 Main Street",
            "city" to "New York",
            "state" to "NY",
            "zipCode" to "10001",
            "country" to "USA"
        )
        example.addHomeAddress(address)

        println("\n✅ All API tests completed successfully!")
    } catch (e: Exception) {
        println("❌ Error during API testing: ${e.message}")
        e.printStackTrace()
    } finally {
        example.cleanup()
        println("🧹 Resources cleaned up")
    }
}
