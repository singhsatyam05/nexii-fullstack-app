package com.swastik.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = NexiiRed,
    onPrimary = Color.Black,
    primaryContainer = NexiiRedDark,
    onPrimaryContainer = Color.White,
    secondary = NexiiRedLight,
    onSecondary = Color.Black,
    secondaryContainer = NexiiRedDark,
    onSecondaryContainer = Color.White,
    tertiary = InfoBlue,
    onTertiary = Color.White,
    tertiaryContainer = InfoBlue,
    onTertiaryContainer = Color.White,
    background = DarkBackground,
    onBackground = DarkOnSurface,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkOnSurfaceVariant,
    error = ErrorRed,
    onError = Color.White,
    outline = DarkOnSurfaceVariant,
    inverseSurface = LightSurface,
    inverseOnSurface = LightOnSurface
)

private val LightColorScheme = lightColorScheme(
    primary = NexiiRed,
    onPrimary = Color.White,
    primaryContainer = NexiiRedLight,
    onPrimaryContainer = Color.White,
    secondary = NexiiRedDark,
    onSecondary = Color.White,
    secondaryContainer = NexiiRed,
    onSecondaryContainer = Color.White,
    tertiary = InfoBlue,
    onTertiary = Color.White,
    tertiaryContainer = InfoBlue,
    onTertiaryContainer = Color.White,
    background = LightBackground,
    onBackground = LightOnSurface,
    surface = LightSurface,
    onSurface = LightOnSurface,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightOnSurfaceVariant,
    error = ErrorRed,
    onError = Color.White,
    outline = LightOnSurfaceVariant,
    inverseSurface = DarkSurface,
    inverseOnSurface = DarkOnSurface
)

@Composable
fun SwastikTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = SwastikTypography,
        shapes = SwastikShapes,
        content = content
    )
}
