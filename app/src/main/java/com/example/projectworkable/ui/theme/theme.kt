package com.example.projectworkable.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Bluish Dark Theme Colors
private val Blue50 = Color(0xFFE3F2FD)
private val Blue100 = Color(0xFFBBDEFB)
private val Blue200 = Color(0xFF90CAF9)
private val Blue300 = Color(0xFF64B5F6)
private val Blue400 = Color(0xFF42A5F5)
private val Blue500 = Color(0xFF2196F3)
private val Blue600 = Color(0xFF1E88E5)
private val Blue700 = Color(0xFF1976D2)
private val Blue800 = Color(0xFF1565C0)
private val Blue900 = Color(0xFF0D47A1)

private val Cyan50 = Color(0xFFE0F7FA)
private val Cyan100 = Color(0xFFB2EBF2)
private val Cyan200 = Color(0xFF80DEEA)
private val Cyan300 = Color(0xFF4DD0E1)
private val Cyan400 = Color(0xFF26C6DA)
private val Cyan500 = Color(0xFF00BCD4)
private val Cyan600 = Color(0xFF00ACC1)
private val Cyan700 = Color(0xFF0097A7)

private val DarkBlue900 = Color(0xFF0A1929)
private val DarkBlue800 = Color(0xFF0F2537)
private val DarkBlue700 = Color(0xFF132F4C)
private val DarkBlue600 = Color(0xFF1E3A56)
private val DarkBlue500 = Color(0xFF2D4862)

// Dark Color Scheme with Bluish Tones
private val DarkColorScheme = darkColorScheme(
    primary = Blue400,              // Main blue accent
    onPrimary = Color.White,
    primaryContainer = Blue700,
    onPrimaryContainer = Blue50,

    secondary = Cyan400,            // Cyan accent
    onSecondary = Color.Black,
    secondaryContainer = Cyan700,
    onSecondaryContainer = Cyan50,

    tertiary = Blue200,
    onTertiary = Color.Black,
    tertiaryContainer = Blue800,
    onTertiaryContainer = Blue100,

    error = Color(0xFFCF6679),
    onError = Color.Black,
    errorContainer = Color(0xFFB00020),
    onErrorContainer = Color(0xFFFCD8DF),

    background = DarkBlue900,       // Very dark blue background
    onBackground = Color(0xFFE1E3E8),

    surface = DarkBlue800,          // Dark blue surface
    onSurface = Color(0xFFE1E3E8),
    surfaceVariant = DarkBlue700,
    onSurfaceVariant = Color(0xFFCAC4D0),

    outline = Blue600,
    outlineVariant = DarkBlue600,

    scrim = Color.Black,
    inverseSurface = Color(0xFFE1E3E8),
    inverseOnSurface = DarkBlue900,
    inversePrimary = Blue600,

    surfaceTint = Blue400,
)

// Light Color Scheme (optional, but good to have)
private val LightColorScheme = lightColorScheme(
    primary = Blue600,
    onPrimary = Color.White,
    primaryContainer = Blue100,
    onPrimaryContainer = Blue900,

    secondary = Cyan600,
    onSecondary = Color.White,
    secondaryContainer = Cyan100,
    onSecondaryContainer = Cyan700,

    tertiary = Blue500,
    onTertiary = Color.White,
    tertiaryContainer = Blue50,
    onTertiaryContainer = Blue900,

    error = Color(0xFFB00020),
    onError = Color.White,
    errorContainer = Color(0xFFFCD8DF),
    onErrorContainer = Color(0xFF410002),

    background = Color(0xFFFCFCFF),
    onBackground = Color(0xFF1A1C1E),

    surface = Color(0xFFFCFCFF),
    onSurface = Color(0xFF1A1C1E),
    surfaceVariant = Color(0xFFE1E2EC),
    onSurfaceVariant = Color(0xFF44464F),

    outline = Blue300,
    outlineVariant = Color(0xFFCAC4D0),

    scrim = Color.Black,
    inverseSurface = Color(0xFF2F3033),
    inverseOnSurface = Color(0xFFF1F0F4),
    inversePrimary = Blue400,

    surfaceTint = Blue600,
)

@Composable
fun WorkableTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}