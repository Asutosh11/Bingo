# Analytics Quick Start

## 🚀 5-Minute Setup

### Android

```kotlin
// In your Activity onCreate()
val adapter = AnalyticsAdapter(
    track = { name, params ->
        Gauge.trackEvent(name, params)  // Replace with your analytics
    },
    setUserIdFn = { id ->
        Gauge.setUserId(id)
    },
    setUserPropertyFn = { key, value ->
        Gauge.setUserProperty(key, value)
    }
)

BingoSDK.initialize(adapter)

// Then launch BingoActivity as usual
startActivity(Intent(this, BingoActivity::class.java))
```

### iOS

```swift
// In AppDelegate didFinishLaunchingWithOptions
let adapter = AnalyticsAdapter(
    track: { name, params in
        Gauge.trackEvent(name: name, params: params)  // Replace with your analytics
    },
    setUserIdFn: { id in
        Gauge.setUserId(id: id)
    },
    setUserPropertyFn: { key, value in
        Gauge.setUserProperty(key: key, value: value)
    }
)

BingoSDK.shared.initialize(adapter: adapter)

// Then present BingoViewController as usual
let vc = BingoViewControllerKt.createBingoViewController()
navigationController?.pushViewController(vc, animated: true)
```

## 📊 Events You'll Get

- ✅ SDK initialization
- ✅ Screen views
- ✅ Button clicks
- ✅ Form validation (success/failure)
- ✅ API calls (started/success/failure)

## 🔧 Works With

- Gauge Analytics
- Firebase Analytics
- Mixpanel
- Amplitude
- Any analytics SDK with event tracking

## 📖 Full Documentation

See `ANALYTICS_INTEGRATION.md` for complete details.
