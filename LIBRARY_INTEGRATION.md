# Bingo SDK - Library Integration Guide

## Overview

The Bingo SDK is a Kotlin Multiplatform library that provides address management functionality with API integration. It supports Android, iOS, and JVM platforms.

## Installation

### Android Integration

#### Option 1: Local AAR (Recommended for development)

1. Build the Android library:
```bash
./gradlew :shared:assembleRelease
```

2. Copy the generated AAR from `shared/build/outputs/aar/shared-release.aar` to your Android project's `libs` folder.

3. Add to your app's `build.gradle.kts`:
```kotlin
dependencies {
    implementation(files("libs/shared-release.aar"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("io.ktor:ktor-client-android:2.3.12")
}
```

#### Option 2: Maven Local

1. Publish to Maven Local:
```bash
./gradlew publishToMavenLocal
```

2. Add to your app's `build.gradle.kts`:
```kotlin
dependencies {
    implementation("com.example.bingo:bingo-sdk-android:1.0.0")
}
```

### iOS Integration

1. Build the iOS framework:
```bash
./gradlew :shared:exportIOSFramework
```

2. Add the generated framework to your Xcode project:
   - Drag `BingoSDK.framework` from `shared/build/bin/iosArm64/releaseFramework/` (for device)
   - Or from `shared/build/bin/iosSimulatorArm64/debugFramework/` (for simulator)

3. In Xcode, go to your target's "Build Phases" → "Link Binary With Libraries" and add `BingoSDK.framework`

## Usage Examples

### Android Usage

```kotlin
import com.example.bingo.BingoSDK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Create address data
        val address = BingoSDK.createAddress(
            street = "123 Main Street",
            city = "New York",
            state = "NY",
            zipCode = "10001",
            country = "USA"
        )
        
        // Validate address
        val validationErrors = BingoSDK.validateAddress(address)
        if (validationErrors.isNotEmpty()) {
            // Handle validation errors
            return
        }
        
        // Add address using Flow (Android/Coroutines approach)
        lifecycleScope.launch {
            BingoSDK.addHomeAddress(address).collect { result ->
                when (result) {
                    is ApiResult.Loading -> {
                        // Show loading indicator
                    }
                    is ApiResult.Success -> {
                        // Handle success: result.data contains AddAddressResponse
                        Log.d("Bingo", "Address added: ${result.data.message}")
                    }
                    is ApiResult.Error -> {
                        // Handle error
                        Log.e("Bingo", "Error: ${result.message}")
                    }
                }
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        BingoSDK.cleanup()
    }
}
```

### iOS Usage (Swift)

```swift
import BingoSDK

class ViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Create address data
        let address = BingoSDK.shared.createAddress(
            street: "123 Main Street",
            city: "New York",
            state: "NY",
            zipCode: "10001",
            country: "USA"
        )
        
        // Validate address
        let validationErrors = BingoSDK.shared.validateAddress(address: address)
        if !validationErrors.isEmpty {
            // Handle validation errors
            return
        }
        
        // Add address using callbacks (iOS-friendly approach)
        BingoSDK.shared.addHomeAddress(
            address: address,
            onLoading: {
                DispatchQueue.main.async {
                    // Show loading indicator
                }
            },
            onSuccess: { response in
                DispatchQueue.main.async {
                    // Handle success
                    print("Address added: \(response.message)")
                }
            },
            onError: { error in
                DispatchQueue.main.async {
                    // Handle error
                    print("Error: \(error)")
                }
            }
        )
    }
    
    deinit {
        BingoSDK.shared.cleanup()
    }
}
```

## API Reference

### BingoSDK

The main entry point for the library.

#### Methods

- `validateAddress(address: Map<String, String>): List<String>`
  - Validates address data
  - Returns list of validation errors (empty if valid)

- `addHomeAddress(address: Map<String, String>): Flow<ApiResult<AddAddressResponse>>`
  - Adds address using Flow (Android/JVM)
  - Returns Flow of ApiResult

- `addHomeAddress(address, onLoading, onSuccess, onError)`
  - Adds address using callbacks (iOS-friendly)
  - Provides separate callbacks for different states

- `createAddress(street, city, state, zipCode, country): Map<String, String>`
  - Helper to create properly formatted address map

- `cleanup()`
  - Cleans up SDK resources

#### Properties

- `VERSION: String` - Current SDK version

## Data Models

### AddAddressResponse
```kotlin
data class AddAddressResponse(
    val message: String,
    val success: Boolean
)
```

### ApiResult<T>
```kotlin
sealed class ApiResult<out T> {
    object Loading : ApiResult<Nothing>()
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}
```

## Building the Library

### For Development
```bash
# Build all targets
./gradlew build

# Build Android library only
./gradlew :shared:assembleRelease

# Build iOS frameworks
./gradlew :shared:exportIOSFramework

# Publish to Maven Local
./gradlew publishToMavenLocal
```

### For Distribution
```bash
# Build and publish all variants
./gradlew publish

# Create distribution package
./gradlew :shared:assembleRelease :shared:exportIOSFramework
```

## Requirements

### Android
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 35
- Kotlin: 2.0.0+

### iOS
- iOS 12.0+
- Xcode 14.0+

### Dependencies
- Ktor Client 2.3.12
- Kotlinx Coroutines 1.7.3
- Kotlinx Serialization

## Troubleshooting

### Android Issues

1. **ProGuard/R8 Issues**: The library includes consumer ProGuard rules that should be automatically applied. If you encounter issues, ensure your app's ProGuard configuration doesn't conflict.

2. **Coroutines Issues**: Make sure you have the correct coroutines dependency for Android:
   ```kotlin
   implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
   ```

### iOS Issues

1. **Framework Not Found**: Ensure the framework is properly added to your Xcode project and the correct architecture is used (device vs simulator).

2. **Import Issues**: Make sure to import `BingoSDK` in your Swift files.

## Support

For issues and questions, please refer to the project documentation or create an issue in the project repository.
