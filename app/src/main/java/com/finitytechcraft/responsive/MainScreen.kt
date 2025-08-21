package com.finitytechcraft.responsive

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.finitytechcraft.responsive.navigation.navigationItems
import com.finitytechcraft.responsive.screens.*
import com.finitytechcraft.responsive.utils.WindowSize
import com.finitytechcraft.responsive.utils.getWindowSize
import com.finitytechcraft.responsive.utils.getResponsivePadding

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val windowSize = getWindowSize()

    when (windowSize) {
        WindowSize.COMPACT -> {
            CompactLayout(navController = navController)
        }
        WindowSize.MEDIUM -> {
            MediumLayout(navController = navController)
        }
        WindowSize.EXPANDED -> {
            ExpandedLayout(navController = navController)
        }
    }
}

@Composable
fun CompactLayout(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavigationHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun MediumLayout(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavigationHost(
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = getResponsivePadding().dp)
        )
    }
}

@Composable
fun ExpandedLayout(navController: NavHostController) {
    Row(modifier = Modifier.fillMaxSize()) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(
                    modifier = Modifier.width(300.dp)
                ) {
                    NavigationDrawerContent(navController = navController)
                }
            }
        ) {
            NavigationHost(
                navController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(getResponsivePadding().dp)
            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Show only first 5 items in bottom navigation to avoid overcrowding
    val bottomNavItems = navigationItems.take(5)

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        tonalElevation = 8.dp
    ) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    if (item.badgeCount != null && item.badgeCount > 0) {
                        BadgedBox(
                            badge = {
                                Badge {
                                    Text(item.badgeCount.toString())
                                }
                            }
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        }
                    } else {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    }
                },
                label = { Text(item.title) },
                selected = currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationDrawerContent(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // App title
        Text(
            text = "Fixware Technologies",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Navigation items
        navigationItems.forEach { item ->
            NavigationDrawerItem(
                icon = {
                    if (item.badgeCount != null && item.badgeCount > 0) {
                        BadgedBox(
                            badge = {
                                Badge {
                                    Text(item.badgeCount.toString())
                                }
                            }
                        ) {
                            Icon(item.icon, contentDescription = item.title)
                        }
                    } else {
                        Icon(item.icon, contentDescription = item.title)
                    }
                },
                label = {
                    Column {
                        Text(item.title)
                        if (item.description.isNotEmpty()) {
                            Text(
                                text = item.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                selected = currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") { HomeScreen() }
        composable("search") { SearchScreen() }
        composable("favorites") { FavoritesScreen() }
        composable("dashboard") { DashboardScreen() }
        composable("profile") { ProfileScreen() }
        composable("settings") { SettingsScreen() }
    }
}