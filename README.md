# 🎯 Bingo

A **Kotlin Multiplatform** project demonstrating API integration using **Ktor Client** for cross-platform networking.

## 🚀 Features

- **Cross-Platform**: Built with Kotlin Multiplatform (KMP) for Android and iOS.
- **Networking**: Uses Ktor Client for HTTP requests with built-in logging

## 🏗️ Architecture

```
📦 Bingo Project Structure
├── 🌐 Network Layer
│   ├── AddressApiService.kt      # HTTP client service
│   └── HttpClient.kt             # Ktor client configuration
├── 📊 Data Layer
│   ├── models/
│   │   └── AddressModels.kt      # Request/Response models
│   └── repository/
│       └── AddressRepository.kt  # Data repository
├── 🎯 Domain Layer
│   └── AddressManager.kt         # Business logic
└── 📱 Examples
    ├── SimpleMain.kt             # Main entry point
    └── AddressApiExample.kt      # Usage examples
```

## 🌍 API Integration

The project integrates with a REST API endpoint:
**Base URL**: `https://composekmp.free.beeceptor.com`  
**Endpoint**: `POST /addaddress`

### Request Model Contract
```json
{
  "data": {
    "street": "123 Main Street",
    "city": "New York",
    "state": "NY",
    "zipCode": "10001",
    "country": "USA",
    "type": "home"
  }
}
```

## 🖥️ Application Output

```
📍 Testing Home Address API...
Adding address...
2025-08-05 19:57:07 INFO HttpClient - REQUEST: https://composekmp.free.beeceptor.com/addaddress
METHOD: HttpMethod(value=POST)
2025-08-05 19:57:08 INFO HttpClient - RESPONSE: 200 OK
METHOD: HttpMethod(value=POST)
FROM: https://composekmp.free.beeceptor.com/addaddress
Address added successfully: Address added

🏢 Testing Work Address API...
Adding work address...
2025-08-05 19:57:08 INFO HttpClient - REQUEST: https://composekmp.free.beeceptor.com/addaddress
METHOD: HttpMethod(value=POST)
2025-08-05 19:57:09 INFO HttpClient - RESPONSE: 200 OK
METHOD: HttpMethod(value=POST)
FROM: https://composekmp.free.beeceptor.com/addaddress
Work address added: Address added

✅ All API tests completed successfully!
🧹 Resources cleaned up