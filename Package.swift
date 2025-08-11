// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "BingoSDK",
    platforms: [
        .iOS(.v12)
    ],
    products: [
        .library(
            name: "BingoSDK",
            targets: ["BingoSDK"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "BingoSDK",
            url: "https://github.com/Asutosh11/Bingo/releases/download/v1.0.2/BingoSDK.xcframework.zip",
            checksum: "your-checksum-here"
        )
    ]
)
