package com.finitytechcraft.responsive.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.finitytechcraft.responsive.utils.getResponsivePadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }
    val padding = getResponsivePadding().dp

    val searchResults = remember(searchText) {
        if (searchText.isBlank()) {
            emptyList()
        } else {
            listOf(
                "Jetpack Compose Tutorial",
                "Android Development",
                "Kotlin Programming",
                "Material Design 3",
                "Responsive UI Design"
            ).filter { it.contains(searchText, ignoreCase = true) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Text(
            text = "Search",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Search bar
        SearchBar(
            query = searchText,
            onQueryChange = { searchText = it },
            onSearch = { isActive = false },
            active = isActive,
            onActiveChange = { isActive = it },
            placeholder = { Text("Search tutorials, courses...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = { /* Filter action */ }) {
                    Icon(Icons.Default.FilterList, contentDescription = "Filter")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            // Search suggestions or recent searches could go here
            if (searchText.isNotEmpty()) {
                LazyColumn {
                    items(searchResults) { result ->
                        ListItem(
                            headlineContent = { Text(result) },
                            modifier = Modifier.clickable {
                                searchText = result
                                isActive = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search results
        if (searchText.isNotEmpty()) {
            Text(
                text = "Results for \"$searchText\"",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (searchResults.isEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No results found",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(searchResults) { result ->
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ListItem(
                                headlineContent = { Text(result) },
                                supportingContent = { Text("Learn about $result with our comprehensive guides") },
                                leadingContent = {
                                    Icon(
                                        Icons.Default.Search,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            )
                        }
                    }
                }
            }
        } else {
            // Popular searches or categories
            Text(
                text = "Popular Categories",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            val categories = listOf(
                "Android Development",
                "Jetpack Compose",
                "Kotlin",
                "Material Design",
                "UI/UX Design"
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { searchText = category }
                    ) {
                        ListItem(
                            headlineContent = { Text(category) },
                            supportingContent = { Text("Explore $category tutorials") },
                            leadingContent = {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}