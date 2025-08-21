package com.finitytechcraft.responsive.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

enum class WindowSize {
    COMPACT,    // Phone portrait (< 600dp)
    MEDIUM,     // Tablet portrait, Phone landscape (600dp - 840dp)
    EXPANDED    // Tablet landscape, Desktop (> 840dp)
}

@Composable
fun getWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    return when {
        configuration.screenWidthDp < 600 -> WindowSize.COMPACT
        configuration.screenWidthDp < 840 -> WindowSize.MEDIUM
        else -> WindowSize.EXPANDED
    }
}

@Composable
fun getScreenWidth(): Int {
    return LocalConfiguration.current.screenWidthDp
}

@Composable
fun getScreenHeight(): Int {
    return LocalConfiguration.current.screenHeightDp
}

// Helper for responsive padding
@Composable
fun getResponsivePadding(): Int {
    return when (getWindowSize()) {
        WindowSize.COMPACT -> 16
        WindowSize.MEDIUM -> 24
        WindowSize.EXPANDED -> 32
    }
}

// Helper for responsive columns
@Composable
fun getResponsiveColumns(): Int {
    return when (getWindowSize()) {
        WindowSize.COMPACT -> 1
        WindowSize.MEDIUM -> 2
        WindowSize.EXPANDED -> 3
    }
}