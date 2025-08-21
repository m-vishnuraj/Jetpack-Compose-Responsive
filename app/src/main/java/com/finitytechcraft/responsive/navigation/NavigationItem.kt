package com.finitytechcraft.responsive.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val badgeCount: Int? = null,
    val description: String = ""
)

val navigationItems = listOf(
    NavigationItem(
        title = "Home",
        icon = Icons.Default.Home,
        route = "home",
        description = "Home dashboard"
    ),
    NavigationItem(
        title = "Search",
        icon = Icons.Default.Search,
        route = "search",
        description = "Search functionality"
    ),
    NavigationItem(
        title = "Favorites",
        icon = Icons.Default.Favorite,
        route = "favorites",
        badgeCount = 5,
        description = "Your favorite items"
    ),
    NavigationItem(
        title = "Dashboard",
        icon = Icons.Default.Dashboard,
        route = "dashboard",
        description = "Analytics dashboard"
    ),
    NavigationItem(
        title = "Profile",
        icon = Icons.Default.Person,
        route = "profile",
        description = "User profile"
    ),
    NavigationItem(
        title = "Settings",
        icon = Icons.Default.Settings,
        route = "settings",
        description = "App settings"
    )
)