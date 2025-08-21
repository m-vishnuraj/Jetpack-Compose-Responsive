package com.finitytechcraft.responsive.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.finitytechcraft.responsive.utils.WindowSize
import com.finitytechcraft.responsive.utils.getWindowSize
import com.finitytechcraft.responsive.utils.getResponsivePadding

data class ChartData(
    val title: String,
    val value: String,
    val change: String,
    val isPositive: Boolean,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun DashboardScreen() {
    val windowSize = getWindowSize()
    val padding = getResponsivePadding().dp

    val chartData = listOf(
        ChartData("Revenue", "$45,231", "+12.5%", true, Icons.Default.BarChart),
        ChartData("Orders", "1,423", "+8.2%", true, Icons.Default.TrendingUp),
        ChartData("Conversion", "3.24%", "-2.1%", false, Icons.Default.PieChart),
        ChartData("Traffic", "89,432", "+15.3%", true, Icons.Default.Timeline)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Text(
            text = "Analytics Dashboard",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Real-time business insights",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        when (windowSize) {
            WindowSize.COMPACT -> {
                CompactDashboard(chartData)
            }
            WindowSize.MEDIUM -> {
                MediumDashboard(chartData)
            }
            WindowSize.EXPANDED -> {
                ExpandedDashboard(chartData)
            }
        }
    }
}

@Composable
fun CompactDashboard(chartData: List<ChartData>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Key Metrics",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(chartData) { data ->
            DashboardCard(data)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Recent Activity",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            RecentActivityCard()
        }
    }
}

@Composable
fun MediumDashboard(chartData: List<ChartData>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Key Metrics",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(chartData.chunked(2)) { rowData ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowData.forEach { data ->
                    Box(modifier = Modifier.weight(1f)) {
                        DashboardCard(data)
                    }
                }
                // Fill remaining space if odd number
                if (rowData.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Recent Activity",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            RecentActivityCard()
        }
    }
}

@Composable
fun ExpandedDashboard(chartData: List<ChartData>) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Left column - metrics
        Column(
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = "Key Metrics",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(chartData.chunked(2)) { rowData ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowData.forEach { data ->
                            Box(modifier = Modifier.weight(1f)) {
                                DashboardCard(data)
                            }
                        }
                    }
                }
            }
        }

        // Right column - activity
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Recent Activity",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            RecentActivityCard()
        }
    }
}

@Composable
fun DashboardCard(data: ChartData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = data.icon,
                    contentDescription = data.title,
                    tint = MaterialTheme.colorScheme.primary
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (data.isPositive) Icons.Default.TrendingUp else Icons.Default.TrendingDown,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (data.isPositive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = data.change,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (data.isPositive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = data.value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = data.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun RecentActivityCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val activities = listOf(
                "New user registration",
                "Order #1234 completed",
                "Payment processed",
                "Analytics updated",
                "System backup completed"
            )

            activities.forEach { activity ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                CircleShape
                            )
                    )
                    Text(
                        text = activity,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }
        }
    }
}