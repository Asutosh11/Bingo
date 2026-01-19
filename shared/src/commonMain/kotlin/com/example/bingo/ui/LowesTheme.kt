package com.example.bingo.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Lowe's brand colors
 */
object LowesColors {
    // Primary Lowe's Blue
    val LowesBlue = Color(0xFF004990)
    val LowesBlueDark = Color(0xFF003366)
    val LowesBlueLight = Color(0xFF1A73E8)
    
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
 * Lowe's Light Color Scheme
 */
private val LowesLightColorScheme = lightColorScheme(
    primary = LowesColors.LowesBlue,
    onPrimary = LowesColors.White,
    primaryContainer = LowesColors.LowesBlueLight,
    onPrimaryContainer = LowesColors.White,
    secondary = LowesColors.LowesBlueDark,
    onSecondary = LowesColors.White,
    background = LowesColors.White,
    onBackground = LowesColors.TextPrimary,
    surface = LowesColors.White,
    onSurface = LowesColors.TextPrimary,
    surfaceVariant = LowesColors.LightGray,
    onSurfaceVariant = LowesColors.TextSecondary,
    error = LowesColors.Error,
    onError = LowesColors.White
)

/**
 * Lowe's Theme wrapper for Compose Multiplatform
 */
@Composable
fun LowesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LowesLightColorScheme,
        typography = Typography(),
        content = content
    )
}
