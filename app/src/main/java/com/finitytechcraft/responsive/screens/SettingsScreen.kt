package com.finitytechcraft.responsive.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.finitytechcraft.responsive.utils.getResponsivePadding

@Composable
fun SettingsScreen() {
    val padding = getResponsivePadding().dp

    var darkModeEnabled by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var pushNotificationsEnabled by remember { mutableStateOf(true) }
    var emailNotificationsEnabled by remember { mutableStateOf(false) }
    var autoBackupEnabled by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }

        // Appearance Section
        item {
            SettingsSection(title = "Appearance") {
                SettingsItem(
                    icon = Icons.Default.DarkMode,
                    title = "Dark Mode",
                    description = "Enable dark theme",
                    action = {
                        Switch(
                            checked = darkModeEnabled,
                            onCheckedChange = { darkModeEnabled = it }
                        )
                    }
                )
            }
        }

        // Notifications Section
        item {
            SettingsSection(title = "Notifications") {
                SettingsItem(
                    icon = Icons.Default.Notifications,
                    title = "Enable Notifications",
                    description = "Receive app notifications",
                    action = {
                        Switch(
                            checked = notificationsEnabled,
                            onCheckedChange = { notificationsEnabled = it }
                        )
                    }
                )

                if (notificationsEnabled) {
                    SettingsItem(
                        title = "Push Notifications",
                        description = "Receive push notifications",
                        isSubItem = true,
                        action = {
                            Switch(
                                checked = pushNotificationsEnabled,
                                onCheckedChange = { pushNotificationsEnabled = it }
                            )
                        }
                    )

                    SettingsItem(
                        title = "Email Notifications",
                        description = "Receive notifications via email",
                        isSubItem = true,
                        action = {
                            Switch(
                                checked = emailNotificationsEnabled,
                                onCheckedChange = { emailNotificationsEnabled = it }
                            )
                        }
                    )
                }
            }
        }

        // App Preferences Section
        item {
            SettingsSection(title = "App Preferences") {
                SettingsItem(
                    icon = Icons.Default.Language,
                    title = "Language",
                    description = "English (US)",
                    onClick = { /* Open language selector */ }
                )

                SettingsItem(
                    icon = Icons.Default.Storage,
                    title = "Auto Backup",
                    description = "Automatically backup your data",
                    action = {
                        Switch(
                            checked = autoBackupEnabled,
                            onCheckedChange = { autoBackupEnabled = it }
                        )
                    }
                )
            }
        }

        // Security Section
        item {
            SettingsSection(title = "Security & Privacy") {
                SettingsItem(
                    icon = Icons.Default.Security,
                    title = "Privacy Settings",
                    description = "Manage your privacy preferences",
                    onClick = { /* Open privacy settings */ }
                )

                SettingsItem(
                    title = "Two-Factor Authentication",
                    description = "Add an extra layer of security",
                    onClick = { /* Open 2FA setup */ }
                )

                SettingsItem(
                    title = "Change Password",
                    description = "Update your account password",
                    onClick = { /* Open password change */ }
                )
            }
        }

        // Support Section
        item {
            SettingsSection(title = "Support") {
                SettingsItem(
                    icon = Icons.Default.Help,
                    title = "Help & Support",
                    description = "Get help and contact support",
                    onClick = { /* Open help */ }
                )

                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "About",
                    description = "App version and information",
                    onClick = { /* Open about */ }
                )
            }
        }

        // Account Section
        item {
            SettingsSection(title = "Account") {
                SettingsItem(
                    icon = Icons.Default.Logout,
                    title = "Sign Out",
                    description = "Sign out of your account",
                    onClick = { /* Sign out */ },
                    isDangerous = true
                )
            }
        }

        // Version info
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Fixware Technologies",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Version 1.0.0",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Build 2024.12.01",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
fun SettingsItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    title: String,
    description: String,
    onClick: (() -> Unit)? = null,
    action: (@Composable () -> Unit)? = null,
    isSubItem: Boolean = false,
    isDangerous: Boolean = false
) {
    val modifier = if (onClick != null) {
        Modifier.clickable { onClick() }
    } else {
        Modifier
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = if (isSubItem) 48.dp else 16.dp,
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null && !isSubItem) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isDangerous) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(end = 16.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (isSubItem) FontWeight.Normal else FontWeight.Medium,
                color = if (isDangerous) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
            )
            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        action?.invoke()
    }
}