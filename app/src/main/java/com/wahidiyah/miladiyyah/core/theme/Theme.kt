package com.wahidiyah.miladiyyah.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColors = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = GreenDark,
    background = SurfaceSoft,
    surface = androidx.compose.ui.graphics.Color.White,
    onBackground = TextDark,
    onSurface = TextDark
)

@Composable
fun WahidiyahTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColors,
        content = content
    )
}
