package com.example.bingo.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Address UI brand colors
 */
object AddressUIColors {
    // Primary Blue
    val PrimaryBlue = Color(0xFF004990)
    val PrimaryBlueDark = Color(0xFF003366)
    val PrimaryBlueLight = Color(0xFF1A73E8)
    
    // Secondary colors
    val White = Color(0xFFFFFFFF)
    val LightGray = Color(0xFFF5F5F5)
    val MediumGray = Color(0xFFE0E0E0)
    val DarkGray = Color(0xFF424242)
    val TextPrimary = Color(0xFF1A1A1A)
    val TextSecondary = Color(0xFF666666)
    
    // Accent
    val Success = Color(0xFF2E7D32)
    val Error = Color(0xFFD32F2F)
}

/**
 * Address UI Light Color Scheme
 */
private val AddressUILightColorScheme = lightColorScheme(
    primary = AddressUIColors.PrimaryBlue,
    onPrimary = AddressUIColors.White,
    primaryContainer = AddressUIColors.PrimaryBlueLight,
    onPrimaryContainer = AddressUIColors.White,
    secondary = AddressUIColors.PrimaryBlueDark,
    onSecondary = AddressUIColors.White,
    background = AddressUIColors.White,
    onBackground = AddressUIColors.TextPrimary,
    surface = AddressUIColors.White,
    onSurface = AddressUIColors.TextPrimary,
    surfaceVariant = AddressUIColors.LightGray,
    onSurfaceVariant = AddressUIColors.TextSecondary,
    error = AddressUIColors.Error,
    onError = AddressUIColors.White
)

/**
 * Address UI Theme wrapper for Compose Multiplatform
 */
@Composable
fun AddressUITheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AddressUILightColorScheme,
        typography = Typography(),
        content = content
    )
}
