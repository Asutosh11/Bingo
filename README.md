# Bingo SDK

A **Kotlin Multiplatform** library for address management and validation with API integration support for Android, iOS, and JVM platforms.

**Latest Version: 1.0.2**

## 📚 Library Integration

For detailed integration instructions and usage examples, see the [**Library Integration Guide**](LIBRARY_INTEGRATION.md).

## Features

- **Cross-Platform**: Built with Kotlin Multiplatform (KMP) for Android and iOS
- **Networking**: Uses Ktor Client for HTTP requests with detailed logging
- **Address Validation**: Built-in validation for address data
- **Dual API Support**: Flow-based for Android, Callback-based for iOS

## Quick Start

### Android
```kotlin
// Flow-based API
BingoSDK.addHomeAddress(address).collect { result ->
    // Handle result
}
```

### iOS
```swift
// Callback-based API
BingoSDK.shared.addHomeAddress(
    address: addressMap,
    onLoading: { /* loading */ },
    onSuccess: { result in /* success */ },
    onError: { error in /* error */ }
)
```

## API Functionalities

### 1. Address Validation

- **validateAddress(address: Map<String, String>): ValidationResult**:
  
  Validates an address map and returns a list of errors if exists else blank

### 2. Address Management API

- **addHomeAddress(address: Map<String, String>)**: Flow-based for Android
- **addHomeAddress(address, onLoading, onSuccess, onError)**: Callback-based for iOS

  **Base URL**: `https://composekmp.free.beeceptor.com`  
  **Endpoint**: `POST /addaddress`

## Sample API Output

```
Testing Home Address API...
Payload: {type=home, street=123 Main Street, city=New York, state=NY, zipCode=10001, country=USA}
HttpClient - REQUEST URL: https://composekmp.free.beeceptor.com/addaddress
METHOD: HttpMethod(value=POST)

Response: 
{ "status": "Address added"}

All API tests completed successfully!
Resources cleaned up
```

## Build Commands

- `./gradlew :shared:build` - Build all targets
- `./gradlew :shared:assembleRelease` - Build Android AAR
- `./gradlew :shared:exportIOSFramework` - Generate iOS frameworks
- `./gradlew publishToMavenLocal` - Publish to local Maven

## Documentation

- [Library Integration Guide](LIBRARY_INTEGRATION.md) - Complete integration instructions
- [API Reference](LIBRARY_INTEGRATION.md#api-reference) - Detailed API documentation
- [Troubleshooting](LIBRARY_INTEGRATION.md#troubleshooting) - Common issues and solutions