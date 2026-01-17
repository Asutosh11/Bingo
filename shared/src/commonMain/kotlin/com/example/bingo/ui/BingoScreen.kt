package com.example.bingo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bingo.BingoSDK

/**
 * Common Compose UI screen for Bingo SDK
 * This screen is shared between Android and iOS
 */
@Composable
fun BingoScreen() {
    // Track screen view on first composition
    LaunchedEffect(Unit) {
        BingoSDK.trackEvent("bingo_screen_viewed", mapOf(
            "sdk_version" to BingoSDK.VERSION
        ))
    }
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome to Bingo SDK",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Version ${BingoSDK.VERSION}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = { 
                    BingoSDK.trackEvent("get_started_clicked", emptyMap())
                    // Add your action here
                },
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text("Get Started")
            }
        }
    }
}
