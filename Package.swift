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
            url: "https://github.com/Asutosh11/Bingo/releases/download/v1.0.6/BingoSDK.xcframework.zip",
            checksum: "4d644b7a6dc3cf7bf9ee0d141c79c46263e0d6c394ff723d02b8f514ca0b83c2"
        )
    ]
)
