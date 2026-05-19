package com.swastik.app.ui.screens.seller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.swastik.app.ui.components.SwastikButton
import com.swastik.app.ui.components.SwastikTextField
import com.swastik.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerRegistrationScreen(
    onSubmit: () -> Unit,
    onBack: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var currentStep by remember { mutableIntStateOf(0) }
    val totalSteps = 4

    // Form fields
    var shopName by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    var gstin by remember { mutableStateOf("") }
    var pan by remember { mutableStateOf("") }
    var bankAccount by remember { mutableStateOf("") }
    var ifsc by remember { mutableStateOf("") }
    var ackTerms by remember { mutableStateOf(false) }

    // Progress
    val progress = (currentStep + 1).toFloat() / totalSteps

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seller Registration") },
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
                    // ProgressBar
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp)),
                        color = NexiiRed,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Step ${currentStep + 1} of $totalSteps",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    SwastikButton(
                        text = if (currentStep < totalSteps - 1) "Next" else "Submit & Pay ₹999",
                        onClick = {
                            if (currentStep < totalSteps - 1) currentStep++ else onSubmit()
                        },
                        icon = if (currentStep < totalSteps - 1) Icons.Filled.ArrowForward else Icons.Filled.CheckCircle
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
            when (currentStep) {
                0 -> {
                    // Shop Details
                    StepHeader("🏪", "Shop Details", "Tell us about your electronics shop")
                    Spacer(modifier = Modifier.height(20.dp))
                    SwastikTextField(value = shopName, onValueChange = { shopName = it }, label = "Shop Name", leadingIcon = Icons.Outlined.Storefront)
                    Spacer(modifier = Modifier.height(14.dp))
                    SwastikTextField(value = ownerName, onValueChange = { ownerName = it }, label = "Owner Full Name", leadingIcon = Icons.Outlined.Person)
                    Spacer(modifier = Modifier.height(14.dp))
                    SwastikTextField(value = phone, onValueChange = { phone = it }, label = "Phone Number", leadingIcon = Icons.Outlined.Phone, keyboardType = KeyboardType.Phone)
                    Spacer(modifier = Modifier.height(14.dp))
                    SwastikTextField(value = email, onValueChange = { email = it }, label = "Email Address", leadingIcon = Icons.Outlined.Email, keyboardType = KeyboardType.Email)
                }
                1 -> {
                    // Address
                    StepHeader("📍", "Shop Address", "Where is your shop located?")
                    Spacer(modifier = Modifier.height(20.dp))
                    SwastikTextField(value = address, onValueChange = { address = it }, label = "Full Address", leadingIcon = Icons.Outlined.LocationOn, singleLine = false, maxLines = 3)
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        SwastikTextField(value = city, onValueChange = { city = it }, label = "City", modifier = Modifier.weight(1f))
                        SwastikTextField(value = state, onValueChange = { state = it }, label = "State", modifier = Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    SwastikTextField(value = pincode, onValueChange = { pincode = it }, label = "Pincode", leadingIcon = Icons.Outlined.Pin, keyboardType = KeyboardType.Number)
                }
                2 -> {
                    // Documents
                    StepHeader("📄", "Business Documents", "Verification requires valid documents")
                    Spacer(modifier = Modifier.height(20.dp))
                    SwastikTextField(value = gstin, onValueChange = { gstin = it }, label = "GSTIN Number", leadingIcon = Icons.Outlined.Receipt)
                    Spacer(modifier = Modifier.height(14.dp))
                    SwastikTextField(value = pan, onValueChange = { pan = it }, label = "PAN Card Number", leadingIcon = Icons.Outlined.CreditCard)

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("Upload Documents", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))

                    DocumentUploadSlot("Shop License")
                    Spacer(modifier = Modifier.height(8.dp))
                    DocumentUploadSlot("GSTIN Certificate")
                    Spacer(modifier = Modifier.height(8.dp))
                    DocumentUploadSlot("PAN Card Photo")
                    Spacer(modifier = Modifier.height(8.dp))
                    DocumentUploadSlot("Shop Interior Photo")
                }
                3 -> {
                    // Bank Details + Terms
                    StepHeader("🏦", "Bank & Payment", "For receiving your earnings")
                    Spacer(modifier = Modifier.height(20.dp))
                    SwastikTextField(value = bankAccount, onValueChange = { bankAccount = it }, label = "Bank Account Number", leadingIcon = Icons.Outlined.AccountBalance, keyboardType = KeyboardType.Number)
                    Spacer(modifier = Modifier.height(14.dp))
                    SwastikTextField(value = ifsc, onValueChange = { ifsc = it }, label = "IFSC Code", leadingIcon = Icons.Outlined.Code)

                    Spacer(modifier = Modifier.height(24.dp))

                    // Platform fee card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = WarningAmber.copy(alpha = 0.1f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("💰 Platform Fee", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("₹999/year — one-time registration + annual fee", style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Includes: Verified badge, seller dashboard, analytics, priority support", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = ackTerms,
                            onCheckedChange = { ackTerms = it },
                            colors = CheckboxDefaults.colors(checkedColor = NexiiRed)
                        )
                        Text("I agree to the Seller Terms & Conditions", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Login for existing sellers
            if (currentStep == 0) {
                OutlinedButton(
                    onClick = onNavigateToLogin,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Already a registered seller? Login here")
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
private fun StepHeader(emoji: String, title: String, subtitle: String) {
    Column {
        Text(emoji, style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun DocumentUploadSlot(label: String) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        shape = RoundedCornerShape(12.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.CloudUpload, "Upload", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(label, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
                    Text("Tap to upload", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            Icon(Icons.Filled.Add, "Add", tint = NexiiRed)
        }
    }
}

