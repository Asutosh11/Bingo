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
            url: "https://github.com/Asutosh11/Bingo/releases/download/v1.0.14/BingoSDK.xcframework.zip",
            checksum: "51baa17950a4bc5743e61c2dfef912291eac6c5437e3a2a9d55761c73e8859fa"
        )
    ]
)
