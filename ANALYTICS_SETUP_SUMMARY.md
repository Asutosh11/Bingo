# Analytics Setup Summary

## Overview

The Bingo SDK now supports analytics integration through a flexible adapter pattern. Parent apps can pass their analytics implementation (like Gauge Analytics) to the SDK, and all user interactions and events will be automatically tracked.

## Files Created

### 1. AnalyticsAdapter.kt
**Location**: `shared/src/commonMain/kotlin/com/example/bingo/analytics/AnalyticsAdapter.kt`

**Purpose**: Core analytics adapter class that accepts three lambda functions:
- `track`: For tracking events with parameters
- `setUserIdFn`: For setting user IDs
- `setUserPropertyFn`: For setting user properties

**Usage**: Parent apps create an instance of this adapter and pass their analytics SDK calls as lambdas.

### 2. ANALYTICS_INTEGRATION.md
**Location**: Root directory

**Purpose**: Comprehensive documentation covering:
- Setup instructions for Android and iOS
- Complete list of tracked events
- Example implementations for Firebase, Mixpanel, Amplitude
- Best practices and troubleshooting

### 3. ANALYTICS_EXAMPLE.md
**Location**: Root directory

**Purpose**: Quick-start examples showing the exact pattern from your screenshot:
- Android implementation
- iOS implementation
- Real Gauge Analytics integration code

## Files Modified

### 1. BingoSDK.kt
**Location**: `shared/src/commonMain/kotlin/com/example/bingo/BingoSDK.kt`

**Changes**:
- Added `analyticsAdapter` property to store the adapter instance
- Added `initialize(adapter: AnalyticsAdapter)` method
- Added internal `trackEvent()` helper method
- Tracks "bingo_sdk_initialized" event on initialization

### 2. AddressManager.kt
**Location**: `shared/src/commonMain/kotlin/com/example/bingo/domain/AddressManager.kt`

**Changes**:
- Added analytics tracking to `validateAddress()` method
  - Tracks "address_validation_success" or "address_validation_failed"
- Added analytics tracking to `addHomeAddress()` callback method
  - Tracks "address_submission_started"
  - Tracks "address_submission_success"
  - Tracks "address_submission_failed"

### 3. BingoScreen.kt
**Location**: `shared/src/commonMain/kotlin/com/example/bingo/ui/BingoScreen.kt`

**Changes**:
- Added screen view tracking using `LaunchedEffect`
  - Tracks "bingo_screen_viewed" when screen is displayed
- Added button click tracking
  - Tracks "get_started_clicked" when button is pressed

### 4. BingoActivity.kt
**Location**: `shared/src/androidMain/kotlin/com/example/bingo/BingoActivity.kt`

**Changes**:
- Updated documentation to show how to initialize SDK with analytics adapter before launching the activity

### 5. BingoViewController.kt
**Location**: `shared/src/iosMain/kotlin/com/example/bingo/BingoViewController.kt`

**Changes**:
- Updated documentation to show how to initialize SDK with analytics adapter in AppDelegate/SceneDelegate

## How It Works

### Initialization Flow

1. **Parent App Creates Adapter**:
   ```kotlin
   val adapter = AnalyticsAdapter(
       track = { name, params -> Gauge.trackEvent(name, params) },
       setUserIdFn = { id -> Gauge.setUserId(id) },
       setUserPropertyFn = { key, value -> Gauge.setUserProperty(key, value) }
   )
   ```

2. **Parent App Initializes SDK**:
   ```kotlin
   BingoSDK.initialize(adapter)
   ```

3. **SDK Stores Adapter**:
   - The adapter is stored in `BingoSDK.analyticsAdapter`
   - First event "bingo_sdk_initialized" is tracked

4. **Automatic Event Tracking**:
   - All SDK components use `BingoSDK.trackEvent()` to track events
   - Events are forwarded to the parent app's analytics SDK via the adapter

### Event Flow

```
User Action → BingoSDK.trackEvent() → AnalyticsAdapter → Parent App's Analytics SDK
```

## Events Tracked

| Event Name | Trigger Point | Parameters |
|------------|--------------|------------|
| `bingo_sdk_initialized` | SDK initialization | version |
| `bingo_screen_viewed` | Screen displayed | sdk_version |
| `get_started_clicked` | Button clicked | none |
| `address_validation_success` | Validation passes | field_count |
| `address_validation_failed` | Validation fails | error_count, errors |
| `address_submission_started` | API call starts | address_type |
| `address_submission_success` | API call succeeds | address_type, response_message |
| `address_submission_failed` | API call fails | address_type, error_message |

## Integration Steps for Parent App

### Android

1. Add initialization code in `onCreate()`:
   ```kotlin
   val adapter = AnalyticsAdapter(
       track = { name, params -> Gauge.trackEvent(name, params) },
       setUserIdFn = { id -> Gauge.setUserId(id) },
       setUserPropertyFn = { key, value -> Gauge.setUserProperty(key, value) }
   )
   BingoSDK.initialize(adapter)
   ```

2. Launch BingoActivity as usual:
   ```kotlin
   val intent = Intent(this, BingoActivity::class.java)
   startActivity(intent)
   ```

### iOS

1. Add initialization code in AppDelegate:
   ```swift
   let adapter = AnalyticsAdapter(
       track: { name, params in Gauge.trackEvent(name: name, params: params) },
       setUserIdFn: { id in Gauge.setUserId(id: id) },
       setUserPropertyFn: { key, value in Gauge.setUserProperty(key: key, value: value) }
   )
   BingoSDK.shared.initialize(adapter: adapter)
   ```

2. Present BingoViewController as usual:
   ```swift
   let viewController = BingoViewControllerKt.createBingoViewController()
   navigationController?.pushViewController(viewController, animated: true)
   ```

## Key Features

✅ **Platform Agnostic**: Works on both Android and iOS
✅ **Flexible**: Supports any analytics SDK (Gauge, Firebase, Mixpanel, Amplitude, etc.)
✅ **Optional**: SDK works without analytics if not initialized
✅ **Type Safe**: Strongly typed Kotlin implementation
✅ **Automatic**: Events are tracked automatically, no manual calls needed
✅ **Comprehensive**: Tracks all major user interactions and API calls

## Testing

To verify analytics integration:

1. Initialize SDK with a test adapter that logs to console
2. Interact with the SDK UI
3. Check that events appear in your analytics dashboard
4. Verify all event parameters are correct

Example test adapter:
```kotlin
val testAdapter = AnalyticsAdapter(
    track = { name, params ->
        println("Analytics Event: $name")
        println("Parameters: $params")
    },
    setUserIdFn = { id -> println("Set User ID: $id") },
    setUserPropertyFn = { key, value -> println("Set Property: $key = $value") }
)
```

## Next Steps

1. Build the project to generate updated artifacts
2. Update version number if needed
3. Create GitHub release with new analytics feature
4. Update LIBRARY_INTEGRATION.md to mention analytics support
5. Share ANALYTICS_INTEGRATION.md with parent app developers
