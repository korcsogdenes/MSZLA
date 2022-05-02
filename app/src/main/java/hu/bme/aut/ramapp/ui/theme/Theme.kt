package hu.bme.aut.ramapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

@Composable
fun ListTheme(darkTheme: Boolean = true, content: @Composable () -> Unit) {

    val DarkColorPalette = darkColors(
        primary = darkGreen,
        primaryVariant = darkGreenV,
        secondary = lightGray,
        surface = graySurface
    )

    val LughtColorPalette = darkColors(
        primary = darkGreen,
        primaryVariant = darkGreenV,
        secondary = graySurface,
        surface = redSurface
    )

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LughtColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}