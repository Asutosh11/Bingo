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
