# Bingo SDK - JitPack Integration

## Android Integration

Add JitPack repository and dependency to your app's `build.gradle.kts`:

```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.Asutosh11:Bingo:1.0.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("io.ktor:ktor-client-android:2.3.12")
}
```

### Usage

#### Option 1: Using BingoActivity (Recommended)

Launch the pre-built Activity:

```kotlin
import android.content.Intent
import com.example.bingo.BingoActivity

// In your Activity or Fragment
val intent = Intent(context, BingoActivity::class.java)
startActivity(intent)
```

#### Option 2: Using Composable directly

Use the Compose UI in your own screen:

```kotlin
import com.example.bingo.ui.BingoScreen

@Composable
fun MyScreen() {
    BingoScreen()
}
```

## iOS Integration

### Option 1: Swift Package Manager

Add to your `Package.swift`:

```swift
dependencies: [
    .package(url: "https://github.com/Asutosh11/Bingo.git", from: "1.0.5")
]
```

### Option 2: CocoaPods

Add to your `Podfile`:

```ruby
pod 'BingoSDK', :git => 'https://github.com/Asutosh11/Bingo.git', :tag => '1.0.5'
```

### Option 3: Manual Framework

1. Download the latest release from: `https://github.com/Asutosh11/Bingo/releases/tag/1.0.5`
2. Add `BingoSDK.xcframework` to your Xcode project
3. In Xcode, go to your target's "Build Phases" → "Link Binary With Libraries" and add `BingoSDK.xcframework`

### Usage

#### Option 1: Using ViewController (UIKit)

```swift
import BingoSDK

// In your UIViewController
let bingoVC = BingoViewControllerKt.createBingoViewController()
navigationController?.pushViewController(bingoVC, animated: true)
// or
present(bingoVC, animated: true)
```

#### Option 2: Using SwiftUI

```swift
import SwiftUI
import BingoSDK

struct BingoView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return BingoViewControllerKt.createBingoViewController()
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

// Use in your SwiftUI view
struct ContentView: View {
    var body: some View {
        BingoView()
    }
}
```
