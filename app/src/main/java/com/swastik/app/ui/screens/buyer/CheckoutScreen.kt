package com.swastik.app.ui.screens.buyer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.swastik.app.data.SampleData
import com.swastik.app.ui.components.SwastikButton
import com.swastik.app.ui.components.SwastikTextField
import com.swastik.app.ui.theme.*
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    onBack: () -> Unit,
    onPlaceOrder: () -> Unit
) {
    val formatter = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    var currentStep by remember { mutableIntStateOf(0) }
    var selectedAddress by remember { mutableIntStateOf(0) }
    var selectedPayment by remember { mutableStateOf("UPI") }

    val steps = listOf("Address", "Payment", "Review")
    val subtotal = 170189.0
    val deliveryFee = 0.0
    val total = subtotal + deliveryFee

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout") },
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentStep > 0) currentStep-- else onBack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total", style = MaterialTheme.typography.titleMedium)
                        Text(formatter.format(total), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, color = NexiiRed)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    SwastikButton(
                        text = if (currentStep < 2) "Continue" else "Place Order",
                        onClick = {
                            if (currentStep < 2) currentStep++ else onPlaceOrder()
                        },
                        icon = if (currentStep < 2) Icons.Filled.ArrowForward else Icons.Filled.CheckCircle
                    )
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
            // ── Step Indicator ──
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                steps.forEachIndexed { index, step ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(50))
                                .background(
                                    when {
                                        index < currentStep -> SuccessGreen
                                        index == currentStep -> NexiiRed
                                        else -> MaterialTheme.colorScheme.surfaceVariant
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (index < currentStep) {
                                Icon(Icons.Filled.Check, "Done", tint = Color.White, modifier = Modifier.size(18.dp))
                            } else {
                                Text("${index + 1}", color = if (index == currentStep) Color.White else MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold)
                            }
                        }
                        if (index < steps.size - 1) {
                            HorizontalDivider(
                                modifier = Modifier
                                    .width(40.dp)
                                    .padding(horizontal = 4.dp),
                                color = if (index < currentStep) SuccessGreen else MaterialTheme.colorScheme.surfaceVariant,
                                thickness = 2.dp
                            )
                        }
                    }
                    if (index < steps.size - 1) {
                        // spacer for the layout
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                steps.forEach { step ->
                    Text(step, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── Step Content ──
            when (currentStep) {
                0 -> AddressStep(selectedAddress) { selectedAddress = it }
                1 -> PaymentStep(selectedPayment) { selectedPayment = it }
                2 -> ReviewStep(formatter, selectedAddress, selectedPayment, total)
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
private fun AddressStep(selectedIndex: Int, onSelect: (Int) -> Unit) {
    val addresses = SampleData.sampleAddresses

    Text("Select Delivery Address", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(16.dp))

    addresses.forEachIndexed { index, address ->
        Card(
            onClick = { onSelect(index) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (selectedIndex == index) NexiiRed.copy(alpha = 0.08f)
                else MaterialTheme.colorScheme.surface
            ),
            border = if (selectedIndex == index) androidx.compose.foundation.BorderStroke(2.dp, NexiiRed) else null
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.Top
            ) {
                RadioButton(
                    selected = selectedIndex == index,
                    onClick = { onSelect(index) },
                    colors = RadioButtonDefaults.colors(selectedColor = NexiiRed)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(address.label, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall)
                    Text(address.fullAddress, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("${address.city}, ${address.state} - ${address.pincode}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedButton(
        onClick = { },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(Icons.Filled.Add, "Add")
        Spacer(modifier = Modifier.width(8.dp))
        Text("Add New Address")
    }
}

@Composable
private fun PaymentStep(selectedMethod: String, onSelect: (String) -> Unit) {
    Text("Select Payment Method", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(16.dp))

    data class PaymentOption(val id: String, val label: String, val icon: ImageVector, val subtitle: String)
    val options = listOf(
        PaymentOption("UPI", "UPI", Icons.Filled.AccountBalance, "Google Pay, PhonePe, Paytm"),
        PaymentOption("Card", "Credit / Debit Card", Icons.Filled.CreditCard, "Visa, Mastercard, RuPay"),
        PaymentOption("NetBanking", "Net Banking", Icons.Filled.Language, "All major banks"),
        PaymentOption("COD", "Cash on Delivery", Icons.Filled.Money, "Pay when delivered"),
        PaymentOption("EMI", "EMI", Icons.Filled.CalendarMonth, "No-cost EMI available")
    )

    options.forEach { option ->
        Card(
            onClick = { onSelect(option.id) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (selectedMethod == option.id) NexiiRed.copy(alpha = 0.08f)
                else MaterialTheme.colorScheme.surface
            ),
            border = if (selectedMethod == option.id) androidx.compose.foundation.BorderStroke(2.dp, NexiiRed) else null
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedMethod == option.id,
                    onClick = { onSelect(option.id) },
                    colors = RadioButtonDefaults.colors(selectedColor = NexiiRed)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(option.icon, option.label, modifier = Modifier.size(28.dp), tint = if (selectedMethod == option.id) NexiiRed else MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(option.label, fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.titleSmall)
                    Text(option.subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
private fun ReviewStep(
    formatter: NumberFormat,
    selectedAddressIndex: Int,
    selectedPayment: String,
    total: Double
) {
    val address = SampleData.sampleAddresses[selectedAddressIndex]

    Text("Order Summary", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(16.dp))

    // Delivery address summary
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("📍 Delivery Address", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text("${address.label}: ${address.fullAddress}", style = MaterialTheme.typography.bodyMedium)
            Text("${address.city}, ${address.state} - ${address.pincode}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Payment method summary
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("💳 Payment Method", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(selectedPayment, style = MaterialTheme.typography.bodyMedium)
        }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Order total
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("💰 Order Total", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                formatter.format(total),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = NexiiRed
            )
            Text("inclusive of all taxes", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Estimated delivery
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(Icons.Filled.LocalShipping, "Delivery", tint = SuccessGreen)
        Text("Estimated delivery: 28 Mar - 30 Mar", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}

