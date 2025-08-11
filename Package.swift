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
            url: "https://github.com/Asutosh11/Bingo/releases/download/v1.0.4/BingoSDK.xcframework.zip",
            checksum: "22dab4b7887e2604a3910fb5bf330f3c36a7e09e0cd0315b4e384f6884f61056"
        )
    ]
)
