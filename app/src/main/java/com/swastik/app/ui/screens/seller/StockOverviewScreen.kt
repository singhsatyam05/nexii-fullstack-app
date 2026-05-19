package com.swastik.app.ui.screens.seller

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.swastik.app.ui.theme.ErrorRed
import com.swastik.app.ui.theme.SuccessGreen
import com.swastik.app.ui.theme.WarningAmber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockOverviewScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Stock Overview", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SuccessGreen.copy(alpha = 0.1f))
                ) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = androidx.compose.ui.graphics.Color.Transparent),
                        headlineContent = { Text("In Stock", fontWeight = FontWeight.Bold) },
                        supportingContent = { Text("18 items have healthy stock levels") },
                        leadingContent = { Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = SuccessGreen) },
                        trailingContent = { Text("18", style = MaterialTheme.typography.titleLarge, color = SuccessGreen) }
                    )
                }
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = WarningAmber.copy(alpha = 0.1f))
                ) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = androidx.compose.ui.graphics.Color.Transparent),
                        headlineContent = { Text("Low Stock", fontWeight = FontWeight.Bold) },
                        supportingContent = { Text("4 items are running low (< 5 units)") },
                        leadingContent = { Icon(Icons.Filled.Warning, contentDescription = null, tint = WarningAmber) },
                        trailingContent = { Text("4", style = MaterialTheme.typography.titleLarge, color = WarningAmber) }
                    )
                }
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = ErrorRed.copy(alpha = 0.1f))
                ) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = androidx.compose.ui.graphics.Color.Transparent),
                        headlineContent = { Text("Out of Stock", fontWeight = FontWeight.Bold) },
                        supportingContent = { Text("2 items are currently unavailable") },
                        leadingContent = { Icon(Icons.Filled.Error, contentDescription = null, tint = ErrorRed) },
                        trailingContent = { Text("2", style = MaterialTheme.typography.titleLarge, color = ErrorRed) }
                    )
                }
            }
        }
    }
}
