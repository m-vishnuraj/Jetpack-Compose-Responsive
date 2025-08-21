package com.finitytechcraft.responsive.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.finitytechcraft.responsive.utils.WindowSize
import com.finitytechcraft.responsive.utils.getWindowSize
import com.finitytechcraft.responsive.utils.getResponsivePadding

data class ProfileInfo(
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val label: String,
    val value: String
)

@Composable
fun ProfileScreen() {
    val windowSize = getWindowSize()
    val padding = getResponsivePadding().dp

    val profileInfo = listOf(
        ProfileInfo(Icons.Default.Email, "Email", "vishnu@fixwaretechnologies.com"),
        ProfileInfo(Icons.Default.Phone, "Phone", "+91 XXXXXXXXXXX"),
        ProfileInfo(Icons.Default.LocationOn, "Location", "Cochin, India"),
        ProfileInfo(Icons.Default.Work, "Department", "Engineering"),
        ProfileInfo(Icons.Default.Person, "Role", "Senior Developer")
    )

    when (windowSize) {
        WindowSize.COMPACT -> CompactProfile(profileInfo, padding)
        WindowSize.MEDIUM -> MediumProfile(profileInfo, padding)
        WindowSize.EXPANDED -> ExpandedProfile(profileInfo, padding)
    }
}

@Composable
fun CompactProfile(profileInfo: List<ProfileInfo>, padding: Dp) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { ProfileHeader() }
        item { ProfileStats() }
        items(profileInfo) { info ->
            ProfileInfoCard(info)
        }
        item { ProfileActions() }
    }
}

@Composable
fun MediumProfile(profileInfo: List<ProfileInfo>, padding: Dp) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { ProfileHeader() }
        item { ProfileStats() }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                profileInfo.chunked(2).forEach { rowInfo ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowInfo.forEach { info ->
                            Box(modifier = Modifier.weight(1f)) {
                                ProfileInfoCard(info)
                            }
                        }
                        if (rowInfo.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
        item { ProfileActions() }
    }
}

@Composable
fun ExpandedProfile(profileInfo: List<ProfileInfo>, padding: Dp) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Left column - Profile info
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProfileHeader()
            ProfileStats()
        }

        // Right column - Details and actions
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Contact Information",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            profileInfo.forEach { info ->
                ProfileInfoCard(info)
            }

            Spacer(modifier = Modifier.height(16.dp))
            ProfileActions()
        }
    }
}

@Composable
fun ProfileHeader() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile picture placeholder
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(60.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Vishnuraj M",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Senior Android Developer",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "Fixware Technologies",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun ProfileStats() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        listOf(
            Triple("Projects", "24", "Completed"),
            Triple("Experience", "5+", "Years"),
            Triple("Rating", "4.9", "Stars")
        ).forEach { (title, value, subtitle) ->
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = value,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileInfoCard(info: ProfileInfo) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = info.icon,
                contentDescription = info.label,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(end = 16.dp)
            )

            Column {
                Text(
                    text = info.label,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = info.value,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun ProfileActions() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = { /* Edit profile */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                Icons.Default.Edit,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Edit Profile")
        }

        OutlinedButton(
            onClick = { /* Change password */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Change Password")
        }

        OutlinedButton(
            onClick = { /* Settings */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Privacy Settings")
        }
    }
}