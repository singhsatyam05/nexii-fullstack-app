package com.swastik.app.ui.screens.seller

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.swastik.app.ui.components.SwastikButton
import com.swastik.app.ui.components.SwastikTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBusinessProfileScreen(onBack: () -> Unit) {
    var businessName by remember { mutableStateOf("Digital Galaxy Store") }
    var ownerName by remember { mutableStateOf("Ravi Kumar") }
    var email by remember { mutableStateOf("contact@digitalgalaxy.in") }
    var phone by remember { mutableStateOf("+91 9876543210") }
    var gstNumber by remember { mutableStateOf("22AAAAA0000A1Z5") }
    var address by remember { mutableStateOf("123 Tech Hub, Sector 14, Gurgaon, Haryana 122001") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Business Profile", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
                        text = "Save Changes",
                        onClick = { onBack() },
                        modifier = Modifier.fillMaxWidth()
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
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SwastikTextField(
                value = businessName,
                onValueChange = { businessName = it },
                label = "Business Name",
                leadingIcon = Icons.Filled.Business
            )
            SwastikTextField(
                value = ownerName,
                onValueChange = { ownerName = it },
                label = "Owner/Contact Person",
                leadingIcon = Icons.Filled.Person
            )
            SwastikTextField(
                value = email,
                onValueChange = { email = it },
                label = "Business Email",
                leadingIcon = Icons.Filled.Email,
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Email
            )
            SwastikTextField(
                value = phone,
                onValueChange = { phone = it },
                label = "Contact Number",
                leadingIcon = Icons.Filled.Phone,
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Phone
            )
            SwastikTextField(
                value = gstNumber,
                onValueChange = { gstNumber = it },
                label = "GSTIN Number",
                leadingIcon = Icons.Filled.Receipt
            )
            SwastikTextField(
                value = address,
                onValueChange = { address = it },
                label = "Shop Address",
                leadingIcon = Icons.Filled.LocationCity,
                singleLine = false,
                modifier = Modifier.height(120.dp)
            )
            
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
