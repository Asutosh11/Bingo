package com.example.bingo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme

/**
 * Android Activity for displaying the Bingo SDK UI
 * 
 * Usage in your Android app:
 * ```
 * val intent = Intent(context, BingoActivity::class.java)
 * context.startActivity(intent)
 * ```
 */
class BingoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                BingoSDK.ComposeUI()
            }
        }
    }
}
