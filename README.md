# Bingo SDK

A **Kotlin Multiplatform** library for validating and adding addresses.

## Features

- **Cross-Platform**: Built with Kotlin Multiplatform (KMP) for Android and iOS
- **Networking**: Uses Ktor Client for HTTP requests with detailed logging

## Functionalities Exposed

### 1. Address Validation Helper

- **validateAddress(address: Map<String, String>): ValidationResult**:

  Validates an address map and returns a list of errors if exists else blank

### 2. Add Address API

- **addHomeAddress(address: Map<String, String>)**: Flow-based for Android
- **addHomeAddress(address, onLoading, onSuccess, onError)**: Callback-based for iOS

  **Base URL**: `https://composekmp.free.beeceptor.com`  
  **Endpoint**: `POST /addaddress`

  **Example API Logs**:
  ```
  Request: {type=home, street=123 Main Street, city=New York, state=NY, zipCode=10001, country=USA}
  2025-08-05 21:06:23 INFO HttpClient - REQUEST: https://composekmp.free.beeceptor.com/addaddress
  METHOD: HttpMethod(value=POST)
  2025-08-05 21:06:24 INFO HttpClient - RESPONSE: 200 OK
  Response: { "status": "Address added" }
  ```



## 📚 Library Integration

For detailed integration instructions and usage examples, see the [**Library Integration Guide**](LIBRARY_INTEGRATION.md).