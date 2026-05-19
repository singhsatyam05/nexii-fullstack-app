package com.swastik.app.ui.screens.buyer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.swastik.app.data.SampleData
import com.swastik.app.data.model.Address
import com.swastik.app.ui.theme.InfoBlue
import com.swastik.app.ui.theme.NexiiRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(onBack: () -> Unit) {
    val addresses: List<Address> = SampleData.sampleAddresses

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Addresses", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* Add address action */ },
                containerColor = NexiiRed,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, "Add New Address")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add Address", fontWeight = FontWeight.Bold)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(addresses) { addr ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (addr.isDefault) InfoBlue.copy(alpha = 0.05f) else MaterialTheme.colorScheme.surface
                    ),
                    border = if (addr.isDefault) CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(InfoBlue)) else null
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = when (addr.label.lowercase()) {
                                    "home" -> Icons.Filled.Home
                                    "office" -> Icons.Filled.Work
                                    else -> Icons.Filled.LocationCity
                                },
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = addr.label,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium
                            )
                            if (addr.isDefault) {
                                Spacer(modifier = Modifier.width(12.dp))
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = InfoBlue.copy(alpha = 0.15f)
                                ) {
                                    Text(
                                        text = "DEFAULT",
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = InfoBlue,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "${addr.fullAddress}\n${addr.city}, ${addr.state} - ${addr.pincode}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(onClick = { }, modifier = Modifier.weight(1f)) {
                                Text("Edit")
                            }
                            OutlinedButton(
                                onClick = { },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = NexiiRed)
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}
