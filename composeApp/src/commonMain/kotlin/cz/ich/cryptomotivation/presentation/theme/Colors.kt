package cz.ich.cryptomotivation.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Light Theme Colors
val LightGrayColor = Color(0xFFB0B0B0)
private val BrownColor = Color(0xFFefd69d)//Color(0xFFd3c5b5)
private val SoftVioletColor = Color(0xFF333046)
private val HardVioletColor = Color(0xFF121021)

// Dark Theme Colors
val DarkGrayColor = Color(0xFF4A4A4A)
val LightBlackColor = Color(0xFF1C1C1C)
val DarkerGrayColor = Color(0xFF2A2A2A)
val DarkWhiteColor = Color(0xFFE0E0E0)

val LightColorScheme = lightColorScheme(
    primary = HardVioletColor,
    background = SoftVioletColor,
    onBackground = BrownColor,
    surface = BrownColor,
    surfaceContainer = BrownColor,
    surfaceVariant = HardVioletColor,
    onSurfaceVariant = BrownColor,
    secondary = LightGrayColor,
)

val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    background = LightBlackColor,
    onBackground = DarkWhiteColor,
    surface = DarkerGrayColor,
    onSurface = Color.White,
    secondary = DarkGrayColor,
    onSecondary = DarkWhiteColor
)