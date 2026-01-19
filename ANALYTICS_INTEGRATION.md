# Analytics Integration Guide

This guide explains how to integrate your analytics SDK (e.g., Gauge Analytics) with the Bingo SDK.

## Overview

The Bingo SDK provides a flexible analytics adapter pattern that allows you to pass your analytics implementation from the parent app. This ensures that all SDK events are tracked through your existing analytics infrastructure.

## Setup

### Android Integration

Initialize the SDK with your analytics adapter before launching the Bingo UI:

```kotlin
import com.example.bingo.BingoSDK
import com.example.bingo.analytics.AnalyticsAdapter

// In your Application class or Activity onCreate
override fun onCreate() {
    super.onCreate()
    
    // Create analytics adapter
    val adapter = AnalyticsAdapter(
        track = { name, params ->
            // Replace with your analytics SDK
            Gauge.trackEvent(name, params)
        },
        setUserIdFn = { id ->
            Gauge.setUserId(id)
        },
        setUserPropertyFn = { key, value ->
            Gauge.setUserProperty(key, value)
        }
    )
    
    // Initialize Bingo SDK
    BingoSDK.initialize(adapter)
    
    // Now you can launch BingoActivity
    val intent = Intent(this, BingoActivity::class.java)
    startActivity(intent)
}
```

### iOS Integration

Initialize the SDK with your analytics adapter in your AppDelegate or SceneDelegate:

```swift
import BingoSDK

// In AppDelegate or SceneDelegate
func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    
    // Create analytics adapter
    let adapter = AnalyticsAdapter(
        track: { name, params in
            // Replace with your analytics SDK
            Gauge.trackEvent(name: name, params: params)
        },
        setUserIdFn: { id in
            Gauge.setUserId(id: id)
        },
        setUserPropertyFn: { key, value in
            Gauge.setUserProperty(key: key, value: value)
        }
    )
    
    // Initialize Bingo SDK
    BingoSDK.shared.initialize(adapter: adapter)
    
    return true
}

// Later, when presenting the Bingo UI
let viewController = BingoViewControllerKt.createBingoViewController()
navigationController?.pushViewController(viewController, animated: true)
```

## Tracked Events

The Bingo SDK automatically tracks the following events:

### SDK Lifecycle Events

| Event Name | Parameters | Description |
|------------|-----------|-------------|
| `bingo_sdk_initialized` | `version`: SDK version | Fired when SDK is initialized |
| `bingo_screen_viewed` | `sdk_version`: SDK version | Fired when Bingo screen is displayed |

### User Interaction Events

| Event Name | Parameters | Description |
|------------|-----------|-------------|
| `get_started_clicked` | None | Fired when "Get Started" button is clicked |

### Address Validation Events

| Event Name | Parameters | Description |
|------------|-----------|-------------|
| `address_validation_success` | `field_count`: Number of fields | Fired when address validation succeeds |
| `address_validation_failed` | `error_count`: Number of errors<br>`errors`: Comma-separated error messages | Fired when address validation fails |

### Address Submission Events

| Event Name | Parameters | Description |
|------------|-----------|-------------|
| `address_submission_started` | `address_type`: Type of address (e.g., "home") | Fired when address submission begins |
| `address_submission_success` | `address_type`: Type of address<br>`response_message`: API response message | Fired when address submission succeeds |
| `address_submission_failed` | `address_type`: Type of address<br>`error_message`: Error message | Fired when address submission fails |

## Analytics Adapter API

### AnalyticsAdapter

The `AnalyticsAdapter` class provides three main functions:

#### Constructor Parameters

```kotlin
AnalyticsAdapter(
    track: (name: String, params: Map<String, Any>) -> Unit,
    setUserIdFn: (id: String) -> Unit,
    setUserPropertyFn: (key: String, value: String) -> Unit
)
```

- **track**: Lambda function to track events with name and parameters
- **setUserIdFn**: Lambda function to set user ID
- **setUserPropertyFn**: Lambda function to set user properties

#### Methods

```kotlin
// Track an event
adapter.trackEvent(name: String, params: Map<String, Any> = emptyMap())

// Set user ID
adapter.setUserId(id: String)

// Set user property
adapter.setUserProperty(key: String, value: String)
```

## Example Implementations

### Firebase Analytics (Android)

```kotlin
val adapter = AnalyticsAdapter(
    track = { name, params ->
        val bundle = Bundle()
        params.forEach { (key, value) ->
            when (value) {
                is String -> bundle.putString(key, value)
                is Int -> bundle.putInt(key, value)
                is Long -> bundle.putLong(key, value)
                is Double -> bundle.putDouble(key, value)
                is Boolean -> bundle.putBoolean(key, value)
            }
        }
        FirebaseAnalytics.getInstance(this).logEvent(name, bundle)
    },
    setUserIdFn = { id ->
        FirebaseAnalytics.getInstance(this).setUserId(id)
    },
    setUserPropertyFn = { key, value ->
        FirebaseAnalytics.getInstance(this).setUserProperty(key, value)
    }
)
```

### Mixpanel (Android)

```kotlin
val mixpanel = MixpanelAPI.getInstance(context, "YOUR_TOKEN")

val adapter = AnalyticsAdapter(
    track = { name, params ->
        val props = JSONObject()
        params.forEach { (key, value) ->
            props.put(key, value)
        }
        mixpanel.track(name, props)
    },
    setUserIdFn = { id ->
        mixpanel.identify(id)
    },
    setUserPropertyFn = { key, value ->
        mixpanel.people.set(key, value)
    }
)
```

### Amplitude (iOS)

```swift
let adapter = AnalyticsAdapter(
    track: { name, params in
        Amplitude.instance().logEvent(name, withEventProperties: params)
    },
    setUserIdFn: { id in
        Amplitude.instance().setUserId(id)
    },
    setUserPropertyFn: { key, value in
        let identify = AMPIdentify()
        identify.set(key, value: value as NSObject)
        Amplitude.instance().identify(identify)
    }
)
```

## Optional Analytics

If you don't want to use analytics, simply don't call `BingoSDK.initialize()`. The SDK will work normally without tracking any events.

## Best Practices

1. **Initialize Early**: Initialize the SDK with analytics adapter as early as possible in your app lifecycle (Application.onCreate for Android, AppDelegate for iOS).

2. **Single Initialization**: Only call `BingoSDK.initialize()` once. Subsequent calls will override the previous adapter.

3. **Error Handling**: Wrap your analytics calls in try-catch blocks to prevent analytics failures from affecting SDK functionality.

4. **Privacy Compliance**: Ensure your analytics implementation complies with privacy regulations (GDPR, CCPA, etc.) and respect user consent preferences.

5. **Testing**: Test analytics integration in debug mode to verify events are being tracked correctly before releasing to production.

## Troubleshooting

### Events Not Appearing

1. Verify that `BingoSDK.initialize()` is called before using any SDK features
2. Check that your analytics SDK is properly configured and initialized
3. Ensure your analytics implementation is not throwing exceptions
4. Verify network connectivity for cloud-based analytics services

### Type Conversion Issues

The analytics adapter accepts `Map<String, Any>` for parameters. Ensure your analytics SDK can handle the following types:
- String
- Int
- Long
- Double
- Boolean

If your analytics SDK requires specific types, add type conversion in your adapter implementation.

## Support

For issues or questions about analytics integration, please refer to:
- Bingo SDK documentation
- Your analytics provider's documentation
- GitHub issues: [Your Repository URL]
