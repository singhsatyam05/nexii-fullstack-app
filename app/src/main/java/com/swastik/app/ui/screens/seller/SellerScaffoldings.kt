package com.swastik.app.ui.screens.seller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.swastik.app.data.UserPreferences
import com.swastik.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerSalesScreen(onBack: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Total Sales") }, navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }) }) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Total Revenue", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("₹1,24,500", style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("+12% from last month", color = SuccessGreen)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text("Recent Transactions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(5) {
                    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                Text("Order #ORD-${it+2034}", fontWeight = FontWeight.Bold)
                                Text("2 items • Today, 2:30 PM", style = MaterialTheme.typography.bodySmall)
                            }
                            Text("₹4,200", fontWeight = FontWeight.Bold, color = NexiiRed)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerOrdersScreen(onBack: () -> Unit, onOrderClick: (String) -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Orders") }, navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }) }) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)) {
            // Status tabs mockup
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                FilterChip(selected = true, onClick = {}, label = { Text("New (3)") })
                FilterChip(selected = false, onClick = {}, label = { Text("Processing") })
                FilterChip(selected = false, onClick = {}, label = { Text("Completed") })
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(3) {
                    val orderId = "ORD-${it+904}"
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onOrderClick(orderId) }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Order #$orderId", fontWeight = FontWeight.Bold)
                                Text("₹1,250", fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Customer: Rahul Kumar", style = MaterialTheme.typography.bodySmall)
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                                Text("Accept Order")
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerSettingsScreen(onBack: () -> Unit, onLogout: () -> Unit) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            icon = { Icon(Icons.AutoMirrored.Filled.Logout, "Logout", tint = NexiiRed) },
            title = { Text("Logout", fontWeight = FontWeight.Bold) },
            text = { Text("Are you sure you want to logout?") },
            confirmButton = {
                TextButton(onClick = {
                    showLogoutDialog = false
                    onLogout()
                }) {
                    Text("Logout", color = NexiiRed, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = { TextButton(onClick = { showLogoutDialog = false }) { Text("Cancel") } }
        )
    }

    Scaffold(topBar = { TopAppBar(title = { Text("Settings") }, navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }) }) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)) {
            Text("Shop Preferences", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            ListItem(
                headlineContent = { Text("Notification Settings") },
                supportingContent = { Text("Manage push & email alerts") },
                leadingContent = { Icon(Icons.Outlined.Notifications, contentDescription = null) }
            )
            HorizontalDivider()
            ListItem(
                headlineContent = { Text("Payment Info") },
                supportingContent = { Text("Bank account and tax details") },
                leadingContent = { Icon(Icons.Outlined.AccountBalance, contentDescription = null) }
            )
            HorizontalDivider()
            
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { showLogoutDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = NexiiRed),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Logout")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerProfileScreen(
    userPreferences: UserPreferences,
    onBack: () -> Unit,
    onSettings: () -> Unit,
    onEditBusiness: () -> Unit,
    onShopDetails: () -> Unit,
    onLogout: () -> Unit,
    onThemeToggle: ((Boolean) -> Unit)? = null
) {
    val userName by userPreferences.userName.collectAsStateWithLifecycle(initialValue = "Seller")
    val userEmail by userPreferences.userEmail.collectAsStateWithLifecycle(initialValue = "")
    val userPhone by userPreferences.userPhone.collectAsStateWithLifecycle(initialValue = "")
    val isDarkTheme by userPreferences.isDarkTheme.collectAsStateWithLifecycle(initialValue = true)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Profile") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(InfoBlue.copy(alpha = 0.15f), MaterialTheme.colorScheme.background)
                        )
                    )
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(modifier = Modifier.size(80.dp), shape = CircleShape, color = InfoBlue) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = userName.firstOrNull()?.uppercase() ?: "S", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = userName.ifEmpty { "Seller" }, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(text = userEmail.ifEmpty { "Not provided" }, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedButton(onClick = onEditBusiness, shape = RoundedCornerShape(12.dp)) {
                        Icon(Icons.Outlined.Edit, "Edit", modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Edit Business Profile")
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                SellerProfileMenuItem(icon = Icons.Outlined.Storefront, title = "Shop Details", subtitle = "View info and address", color = InfoBlue, onClick = onShopDetails)
                SellerProfileMenuItem(icon = Icons.Outlined.Settings, title = "Settings", subtitle = "App preferences", color = ElectricBlue, onClick = onSettings)
                
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                SellerProfileMenuItem(icon = Icons.AutoMirrored.Filled.Logout, title = "Logout", subtitle = "Logout from your account", color = NexiiRed, onClick = onLogout)
                
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                
                Card(
                    onClick = { onThemeToggle?.invoke(!isDarkTheme) },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(44.dp),
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    if (isDarkTheme) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                                    "Theme Toggle",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "App Theme", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                            Text(text = if (isDarkTheme) "Dark Mode" else "Light Mode", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Switch(checked = isDarkTheme, onCheckedChange = { onThemeToggle?.invoke(it) })
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                SellerProfileMenuItem(icon = Icons.Outlined.HeadsetMic, title = "Seller Support", subtitle = "Get help from our team", color = SuccessGreen, onClick = {})
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun SellerProfileMenuItem(icon: ImageVector, title: String, subtitle: String, color: Color, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(44.dp), shape = RoundedCornerShape(12.dp), color = color.copy(alpha = 0.1f)) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(icon, title, tint = color, modifier = Modifier.size(22.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Icon(Icons.Filled.ChevronRight, "Navigate", tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(20.dp))
        }
    }
}
