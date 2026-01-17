package com.example.bingo

import androidx.compose.ui.window.ComposeUIViewController
import com.example.bingo.ui.BingoScreen

fun createBingoViewController() = ComposeUIViewController {
    BingoScreen()
}
