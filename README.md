# Bingo SDK

[![JitPack](https://jitpack.io/v/Asutosh11/Bingo.svg)](https://jitpack.io/#Asutosh11/Bingo)

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

## 📱 Demo: Library Integration in iOS

Here's a visual demonstration of the Bingo SDK integrated into an iOS application:

### iOS Simulator Screenshots

<table>
  <tr>
    <td><img src="screenshots/Simulator%20Screenshot%20-%20iPhone%2016%20Pro%20-%202025-08-13%20at%2018.57.30.png" width="200"/></td>
    <td><img src="screenshots/Simulator%20Screenshot%20-%20iPhone%2016%20Pro%20-%202025-08-13%20at%2018.58.44.png" width="200"/></td>
  </tr>
  <tr>
    <td><img src="screenshots/Simulator%20Screenshot%20-%20iPhone%2016%20Pro%20-%202025-08-13%20at%2018.58.49.png" width="200"/></td>
    <td><img src="screenshots/Simulator%20Screenshot%20-%20iPhone%2016%20Pro%20-%202025-08-13%20at%2018.58.54.png" width="200"/></td>
  </tr>
</table>

![Integration Overview](screenshots/Screenshot%202025-08-13%20at%207.03.24%20PM.png)