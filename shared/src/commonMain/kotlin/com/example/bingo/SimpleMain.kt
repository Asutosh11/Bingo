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
        example.addHomeAddressExample()
        
        println("\n🏢 Testing Work Address API...")
        example.addWorkAddressExample()
        
        println("\n🔧 Testing Custom Address API...")
        example.addCustomAddressExample()
        
        println("\n📄 Testing JSON Address API...")
        example.addAddressFromJsonExample()
        
        println("\n✅ All API tests completed successfully!")
        
    } catch (e: Exception) {
        println("❌ Error during API testing: ${e.message}")
        e.printStackTrace()
    } finally {
        example.cleanup()
        println("🧹 Resources cleaned up")
    }
}
