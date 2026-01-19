# Analytics Integration Example

This example shows exactly how to integrate Gauge Analytics (or any analytics SDK) with Bingo SDK, matching the pattern from your parent app.

## Android Example

```kotlin
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.bingo.BingoSDK
import com.example.bingo.analytics.AnalyticsAdapter
import android.content.Intent
import com.example.bingo.BingoActivity

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize Bingo SDK with Gauge Analytics adapter
        val adapter = AnalyticsAdapter(
            track = { name, params ->
                // Replace with your analytics SDK
                // Example: Gauge.trackEvent(name, params)
            },
            setUserIdFn = { id ->
                // Example: Gauge.setUserId(id)
            },
            setUserPropertyFn = { key, value ->
                // Example: Gauge.setUserProperty(key, value)
            }
        )
        
        BingoSDK.initialize(adapter)
        
        // Launch Bingo Activity
        val intent = Intent(this, BingoActivity::class.java)
        startActivity(intent)
    }
}
```

## iOS Example (Swift)

```swift
import UIKit
import BingoSDK

class ViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Initialize Bingo SDK with Gauge Analytics adapter
        let adapter = AnalyticsAdapter(
            track: { name, params in
                // Replace with your analytics SDK
                // Example: Gauge.trackEvent(name: name, params: params)
            },
            setUserIdFn: { id in
                // Example: Gauge.setUserId(id: id)
            },
            setUserPropertyFn: { key, value in
                // Example: Gauge.setUserProperty(key: key, value: value)
            }
        )
        
        BingoSDK.shared.initialize(adapter: adapter)
    }
    
    @IBAction func openBingoSDK(_ sender: Any) {
        let viewController = BingoViewControllerKt.createBingoViewController()
        navigationController?.pushViewController(viewController, animated: true)
    }
}
```

## Real Gauge Analytics Implementation

If you're using the actual Gauge Analytics SDK:

### Android

```kotlin
import com.gauge.android.Gauge

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    val adapter = AnalyticsAdapter(
        track = { name, params ->
            Gauge.trackEvent(name, params)
        },
        setUserIdFn = { id ->
            Gauge.setUserId(id)
        },
        setUserPropertyFn = { key, value ->
            Gauge.setUserProperty(key, value)
        }
    )
    
    BingoSDK.initialize(adapter)
}
```

### iOS

```swift
import Gauge

let adapter = AnalyticsAdapter(
    track: { name, params in
        Gauge.trackEvent(name: name, params: params)
    },
    setUserIdFn: { id in
        Gauge.setUserId(id: id)
    },
    setUserPropertyFn: { key, value in
        Gauge.setUserProperty(key: key, value: value)
    }
)

BingoSDK.shared.initialize(adapter: adapter)
```

## Events Tracked by Bingo SDK

Once initialized, the Bingo SDK will automatically track these events through your analytics adapter:

1. **bingo_sdk_initialized** - When SDK is initialized
2. **bingo_screen_viewed** - When Bingo screen is displayed
3. **get_started_clicked** - When user clicks "Get Started"
4. **address_validation_success** - When address validation passes
5. **address_validation_failed** - When address validation fails
6. **address_submission_started** - When address submission begins
7. **address_submission_success** - When address is successfully submitted
8. **address_submission_failed** - When address submission fails

All these events will appear in your Gauge Analytics dashboard (or whichever analytics platform you're using).
