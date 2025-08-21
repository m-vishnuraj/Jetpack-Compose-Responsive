package com.finitytechcraft.responsive.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.finitytechcraft.responsive.utils.WindowSize
import com.finitytechcraft.responsive.utils.getWindowSize
import com.finitytechcraft.responsive.utils.getResponsiveColumns
import com.finitytechcraft.responsive.utils.getResponsivePadding

data class StatsCard(
    val title: String,
    val value: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val change: String
)

@Composable
fun HomeScreen() {
    val windowSize = getWindowSize()
    val padding = getResponsivePadding().dp

    val statsData = listOf(
        StatsCard("Revenue", "$24,567", Icons.Default.AttachMoney, "+12%"),
        StatsCard("Users", "1,234", Icons.Default.People, "+8%"),
        StatsCard("Growth", "45%", Icons.Default.TrendingUp, "+3%"),
        StatsCard("Analytics", "98.5%", Icons.Default.Analytics, "+2%")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        // Welcome section
        Text(
            text = "Welcome to Finity TechCraft",
            style = when (windowSize) {
                WindowSize.COMPACT -> MaterialTheme.typography.headlineMedium
                WindowSize.MEDIUM -> MaterialTheme.typography.headlineLarge
                WindowSize.EXPANDED -> MaterialTheme.typography.displaySmall
            },
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Your responsive dashboard",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Screen size indicator
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = "Current Layout: ${windowSize.name}",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Stats grid
        when (windowSize) {
            WindowSize.COMPACT -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(statsData) { stat ->
                        StatsCardComponent(stat)
                    }
                }
            }
            WindowSize.MEDIUM, WindowSize.EXPANDED -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(getResponsiveColumns()),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(statsData) { stat ->
                        StatsCardComponent(stat)
                    }
                }
            }
        }
    }
}

@Composable
fun StatsCardComponent(stats: StatsCard) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = stats.icon,
                    contentDescription = stats.title,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stats.change,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Column {
                Text(
                    text = stats.value,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stats.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}