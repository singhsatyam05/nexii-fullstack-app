package com.swastik.app.ui.screens.buyer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.draw.clip
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
fun ProfileScreen(
    userPreferences: UserPreferences,
    onOrderHistory: () -> Unit,
    onBack: () -> Unit,
    onLogout: () -> Unit,
    onEditProfile: () -> Unit,
    onCustomerSupport: () -> Unit,
    onManageAddresses: () -> Unit,
    onPaymentMethods: () -> Unit,
    onNotifications: () -> Unit,
    onAbout: () -> Unit,
    onThemeToggle: ((Boolean) -> Unit)? = null // Injecting explicitly since we need to mutate the pref
) {
    val userName by userPreferences.userName.collectAsStateWithLifecycle(initialValue = "User")
    val userEmail by userPreferences.userEmail.collectAsStateWithLifecycle(initialValue = "")
    val userPhone by userPreferences.userPhone.collectAsStateWithLifecycle(initialValue = "")
    val isDarkTheme by userPreferences.isDarkTheme.collectAsStateWithLifecycle(initialValue = true)

    var showLogoutDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var showSupportDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            snackbarMessage = null
        }
    }

    // Logout confirmation dialog
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
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Edit Profile logic moved to dedicated screen.

    // Support dialog
    if (showSupportDialog) {
        AlertDialog(
            onDismissRequest = { showSupportDialog = false },
            icon = { Text("💬", fontSize = 28.sp) },
            title = { Text("Customer Support", fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text("📧 support@swastik.com", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("📞 1800-123-4567 (Toll Free)", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("⏰ Mon-Sat, 9 AM - 8 PM", style = MaterialTheme.typography.bodyMedium)
                }
            },
            confirmButton = {
                TextButton(onClick = { showSupportDialog = false }) {
                    Text("OK", color = NexiiRed, fontWeight = FontWeight.Bold)
                }
            }
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("My Profile") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Profile header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                NexiiRed.copy(alpha = 0.15f),
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Avatar
                    Surface(
                        modifier = Modifier.size(80.dp),
                        shape = CircleShape,
                        color = NexiiRed
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = userName.firstOrNull()?.uppercase() ?: "U",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = userName.ifEmpty { "User" },
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = userEmail.ifEmpty { "Not provided" },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = onEditProfile,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Outlined.Edit, "Edit", modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Edit Profile")
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Menu items
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                ProfileMenuItem(
                    icon = Icons.Outlined.ShoppingBag,
                    title = "My Orders",
                    subtitle = "View order history & track orders",
                    color = NexiiRed,
                    onClick = onOrderHistory
                )

                ProfileMenuItem(
                    icon = Icons.Outlined.LocationOn,
                    title = "Manage Addresses",
                    subtitle = "Add or edit delivery addresses",
                    color = InfoBlue,
                    onClick = onManageAddresses
                )

                ProfileMenuItem(
                    icon = Icons.Outlined.CreditCard,
                    title = "Payment Methods",
                    subtitle = "Manage your payment options",
                    color = ElectricBlue,
                    onClick = onPaymentMethods
                )

                ProfileMenuItem(
                    icon = Icons.Outlined.Notifications,
                    title = "Notifications",
                    subtitle = "Manage notification preferences",
                    color = WarningAmber,
                    onClick = onNotifications
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                Card(
                    onClick = { onThemeToggle?.invoke(!isDarkTheme) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
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
                            Text(
                                text = "App Theme",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = if (isDarkTheme) "Dark Mode" else "Light Mode",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = { onThemeToggle?.invoke(it) }
                        )
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                ProfileMenuItem(
                    icon = Icons.Outlined.HeadsetMic,
                    title = "Customer Support",
                    subtitle = "Get help from our team",
                    color = SuccessGreen,
                    onClick = onCustomerSupport
                )

                ProfileMenuItem(
                    icon = Icons.Outlined.Info,
                    title = "About NEXII",
                    subtitle = "Version 1.0.0",
                    color = InfoBlue,
                    onClick = onAbout
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                ProfileMenuItem(
                    icon = Icons.AutoMirrored.Filled.Logout,
                    title = "Logout",
                    subtitle = "Sign out of your account",
                    color = NexiiRed,
                    onClick = { showLogoutDialog = true }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(44.dp),
                shape = RoundedCornerShape(12.dp),
                color = color.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(icon, title, tint = color, modifier = Modifier.size(22.dp))
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                Icons.Filled.ChevronRight,
                "Navigate",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

