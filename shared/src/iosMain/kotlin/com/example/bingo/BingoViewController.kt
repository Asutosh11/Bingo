package com.example.bingo

import androidx.compose.ui.window.ComposeUIViewController

/**
 * iOS ViewController for displaying the Bingo SDK UI
 * 
 * Usage in your iOS app (Swift):
 * ```swift
 * import BingoSDK
 * 
 * let viewController = BingoViewControllerKt.createBingoViewController()
 * navigationController?.pushViewController(viewController, animated: true)
 * ```
 */
fun createBingoViewController() = ComposeUIViewController {
    BingoSDK.ComposeUI()
}
