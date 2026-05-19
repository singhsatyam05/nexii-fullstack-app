package com.swastik.app.ui.screens.seller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.swastik.app.ui.components.SwastikButton
import com.swastik.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailsScreen(
    orderId: String,
    onBack: () -> Unit
) {
    val status = when (orderId) {
        "ORD-001" -> "Pending"
        "ORD-002" -> "Confirmed"
        "ORD-003" -> "Shipped"
        "ORD-004", "ORD-111" -> "Delivered"
        else -> "Pending" // Default
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Order Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        bottomBar = {
            if (status != "Delivered") {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shadowElevation = 8.dp,
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        SwastikButton(
                            text = "Mark as Processed & Ready",
                            onClick = { },
                            icon = Icons.Filled.CheckCircle
                        )
                    }
                }
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
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = orderId,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = "Placed exactly 2 hours ago",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = when (status) {
                        "Pending" -> WarningAmber.copy(alpha = 0.15f)
                        "Confirmed", "Shipped" -> InfoBlue.copy(alpha = 0.15f)
                        else -> SuccessGreen.copy(alpha = 0.15f)
                    }
                ) {
                    Text(
                        text = status,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelMedium,
                        color = when (status) {
                            "Pending" -> WarningAmber
                            "Confirmed", "Shipped" -> InfoBlue
                            else -> SuccessGreen
                        }
                    )
                }
            }

            if (status == "Delivered") {
                Spacer(modifier = Modifier.height(24.dp))
                Text("Tracking Timeline", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    TrackingStep(title = "Order Placed", time = "10:00 AM", isCompleted = true, isLast = false)
                    TrackingStep(title = "Processing by Seller", time = "10:15 AM", isCompleted = true, isLast = false)
                    TrackingStep(title = "Picked up by Courier", time = "10:45 AM", isCompleted = true, isLast = false)
                    TrackingStep(title = "Out for Delivery", time = "11:10 AM", isCompleted = true, isLast = false)
                    TrackingStep(title = "Delivered to Customer", time = "11:35 AM", isCompleted = true, isLast = true)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Items
            Text("Order Items", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OrderItemRow("iPhone 15 Pro Max", "1", "₹1,44,900")
                    Divider(modifier = Modifier.padding(vertical = 12.dp))
                    OrderItemRow("Apple 20W Power Adapter", "1", "₹1,900")
                    Divider(modifier = Modifier.padding(vertical = 12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Total Amount:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                        Text("₹1,46,800", fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.titleLarge, color = SuccessGreen)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Customer Info
            Text("Customer Information", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = InfoBlue.copy(alpha = 0.05f)),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier.size(40.dp).background(InfoBlue.copy(alpha = 0.2f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.Person, contentDescription = null, tint = InfoBlue)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Arjun Mehta", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                            Text("Verified Buyer", style = MaterialTheme.typography.labelSmall, color = SuccessGreen)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Phone, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("+91 9876543210", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (status != "Delivered") {
                // Delivery Protocol
                Text("Logistics", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Card(modifier = Modifier.weight(1f), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Filled.Inventory, "Pack", tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Pack Order", fontWeight = FontWeight.Bold)
                            Text("Use NEXII Boxes", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                    Card(modifier = Modifier.weight(1f), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Filled.DirectionsCar, "Rider", tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Assign Rider", fontWeight = FontWeight.Bold)
                            Text("Looking for nearby...", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun TrackingStep(title: String, time: String, isCompleted: Boolean, isLast: Boolean) {
    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(if (isCompleted) SuccessGreen else MaterialTheme.colorScheme.surfaceVariant)
            )
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f)
                        .background(if (isCompleted) SuccessGreen else MaterialTheme.colorScheme.surfaceVariant)
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.padding(bottom = 24.dp)) {
            Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
            Text(time, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
fun OrderItemRow(name: String, qty: String, price: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(name, fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyLarge)
            Text("Qty: $qty", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Text(price, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
    }
}
