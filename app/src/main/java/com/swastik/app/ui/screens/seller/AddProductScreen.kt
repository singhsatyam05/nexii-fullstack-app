package com.swastik.app.ui.screens.seller

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.swastik.app.data.SampleData
import com.swastik.app.ui.components.SwastikButton
import com.swastik.app.ui.components.SwastikTextField
import com.swastik.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var brand by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var mrp by remember { mutableStateOf("") }
    var sellingPrice by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var warranty by remember { mutableStateOf("") }
    var manufacturer by remember { mutableStateOf("") }

    // Spinner / Dropdown for category
    var selectedCategory by remember { mutableStateOf("") }
    var categoryExpanded by remember { mutableStateOf(false) }
    val categories = SampleData.categories.map { it.name }

    // Specification fields
    var specKey by remember { mutableStateOf("") }
    var specValue by remember { mutableStateOf("") }
    var specs by remember { mutableStateOf<List<Pair<String, String>>>(emptyList()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Product") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
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
                    SwastikButton(
                        text = "Save Product",
                        onClick = onSave,
                        icon = Icons.Filled.Save
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
            // ── Upload Images ──
            Text("Product Images", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Upload at least 3 photos", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(4) {
                    OutlinedCard(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Outlined.AddPhotoAlternate,
                                "Add Photo",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── Basic Info ──
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Basic Information", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(16.dp))

            SwastikTextField(value = name, onValueChange = { name = it }, label = "Product Name", leadingIcon = Icons.Outlined.ShoppingBag)
            Spacer(modifier = Modifier.height(14.dp))
            SwastikTextField(value = brand, onValueChange = { brand = it }, label = "Brand Name", leadingIcon = Icons.Outlined.Label)

            Spacer(modifier = Modifier.height(14.dp))

            // Category Spinner/Dropdown
            ExposedDropdownMenuBox(
                expanded = categoryExpanded,
                onExpandedChange = { categoryExpanded = !categoryExpanded }
            ) {
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Category") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    shape = RoundedCornerShape(12.dp)
                )
                ExposedDropdownMenu(
                    expanded = categoryExpanded,
                    onDismissRequest = { categoryExpanded = false }
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = {
                                selectedCategory = category
                                categoryExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            SwastikTextField(
                value = description,
                onValueChange = { description = it },
                label = "Product Description",
                leadingIcon = Icons.Outlined.Description,
                singleLine = false,
                maxLines = 4
            )

                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── Pricing ──
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Pricing & Inventory", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SwastikTextField(
                    value = mrp,
                    onValueChange = { mrp = it },
                    label = "MRP (₹)",
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier.weight(1f)
                )
                SwastikTextField(
                    value = sellingPrice,
                    onValueChange = { sellingPrice = it },
                    label = "Selling Price (₹)",
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier.weight(1f)
                )
            }

            // Discount auto-calc
            if (mrp.isNotEmpty() && sellingPrice.isNotEmpty()) {
                val m = mrp.toDoubleOrNull() ?: 0.0
                val s = sellingPrice.toDoubleOrNull() ?: 0.0
                if (m > 0 && s < m) {
                    val discount = ((m - s) / m * 100).toInt()
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "💰 Discount: $discount% OFF",
                        style = MaterialTheme.typography.bodySmall,
                        color = SuccessGreen,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            SwastikTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = "Stock Quantity",
                leadingIcon = Icons.Outlined.Inventory2,
                keyboardType = KeyboardType.Number
            )

                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── Specifications ──
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Specifications", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(16.dp))

            // Existing specs
            specs.forEach { (key, value) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("$key: $value", style = MaterialTheme.typography.bodyMedium)
                    IconButton(onClick = { specs = specs.filter { it.first != key } }, modifier = Modifier.size(24.dp)) {
                        Icon(Icons.Filled.Close, "Remove", modifier = Modifier.size(16.dp), tint = ErrorRed)
                    }
                }
            }

            // Add spec
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                SwastikTextField(value = specKey, onValueChange = { specKey = it }, label = "Key", modifier = Modifier.weight(1f))
                SwastikTextField(value = specValue, onValueChange = { specValue = it }, label = "Value", modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        if (specKey.isNotEmpty() && specValue.isNotEmpty()) {
                            specs = specs + (specKey to specValue)
                            specKey = ""
                            specValue = ""
                        }
                    }
                ) {
                    Icon(Icons.Filled.AddCircle, "Add Spec", tint = NexiiRed, modifier = Modifier.size(32.dp))
                }
            }

                    }
                }

            Spacer(modifier = Modifier.height(24.dp))

            // ── Additional Details ──
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Additional Details", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(16.dp))

            SwastikTextField(value = warranty, onValueChange = { warranty = it }, label = "Warranty Info", leadingIcon = Icons.Outlined.VerifiedUser)
                    Spacer(modifier = Modifier.height(14.dp))
                    SwastikTextField(value = manufacturer, onValueChange = { manufacturer = it }, label = "Manufacturer Details", leadingIcon = Icons.Outlined.Factory, singleLine = false, maxLines = 2)
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }



}
