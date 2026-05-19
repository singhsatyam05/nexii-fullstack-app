package com.swastik.app.ui.screens.seller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import com.swastik.app.ui.theme.*
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerDashboardScreen(
    onAddProduct: () -> Unit,
    onManageProducts: () -> Unit,
    onSales: () -> Unit,
    onOrders: () -> Unit,
    onSettings: () -> Unit,
    onProfile: () -> Unit,
    onStockOverview: () -> Unit
) {
    val formatter = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Seller Dashboard", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text("Digital Galaxy Store", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                },
                actions = {
                    IconButton(onClick = onProfile) {
                        Icon(Icons.Filled.Person, "Profile")
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAddProduct,
                containerColor = NexiiRed,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, "Add Product")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add Product", fontWeight = FontWeight.Bold)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // ── Stats Cards ──
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard("Total Sales", formatter.format(287450), Icons.Filled.CurrencyRupee, SuccessGreen, Modifier.weight(1f), onClick = onSales)
                StatCard("Orders", "42", Icons.Filled.ShoppingBag, InfoBlue, Modifier.weight(1f), onClick = onOrders)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard("Products", "24", Icons.Filled.Inventory, MaterialTheme.colorScheme.primary, Modifier.weight(1f), onClick = onStockOverview)
                StatCard("Views", "1.2K", Icons.Filled.Visibility, WarningAmber, Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── Quick Actions ──
            Text("Quick Actions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionCard("Add Product", "📦", NexiiRed, Modifier.weight(1f)) { onAddProduct() }
                ActionCard("My Products", "📋", InfoBlue, Modifier.weight(1f)) { onManageProducts() }
                ActionCard("Orders", "🛍️", SuccessGreen, Modifier.weight(1f)) { onOrders() }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── Recent Orders ──
            Text("Recent Orders", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            listOf(
                Triple("ORD-001", "iPhone 15 Pro Max × 1", "Pending"),
                Triple("ORD-002", "Sony WH-1000XM5 × 1", "Confirmed"),
                Triple("ORD-003", "boAt Airdopes × 2", "Shipped"),
                Triple("ORD-004", "Samsung Galaxy S24 × 1", "Delivered")
            ).forEach { (orderId, items, status) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(orderId, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall)
                            Text(items, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = when (status) {
                                "Pending" -> WarningAmber.copy(alpha = 0.15f)
                                "Confirmed" -> InfoBlue.copy(alpha = 0.15f)
                                else -> SuccessGreen.copy(alpha = 0.15f)
                            }
                        ) {
                            Text(
                                status,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.labelMedium,
                                color = when (status) {
                                    "Pending" -> WarningAmber
                                    "Confirmed" -> InfoBlue
                                    else -> SuccessGreen
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── Earnings Card ──
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkCard)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("💰 This Month's Earnings", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(formatter.format(87250), style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.ExtraBold, color = SuccessGreen)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Commission paid: ${formatter.format(8725)}", style = MaterialTheme.typography.bodySmall, color = DarkOnSurfaceVariant)
                    Spacer(modifier = Modifier.height(12.dp))
                    LinearProgressIndicator(
                        progress = { 0.65f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = SuccessGreen,
                        trackColor = DarkSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("65% of monthly target", style = MaterialTheme.typography.labelSmall, color = DarkOnSurfaceVariant)
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StatCard(
    title: String,
    value: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(icon, title, tint = color, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, color = color)
            Text(title, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun ActionCard(
    title: String,
    emoji: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(emoji, fontSize = 28.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(title, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Medium)
        }
    }
}
