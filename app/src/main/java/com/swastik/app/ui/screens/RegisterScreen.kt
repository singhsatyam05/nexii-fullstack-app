package com.swastik.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swastik.app.ui.components.SwastikButton
import com.swastik.app.ui.components.SwastikTextField
import com.swastik.app.ui.theme.*

@Composable
fun RegisterScreen(
    onRegister: (name: String, email: String, phone: String) -> Unit,
    onBackToLogin: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var agreeToTerms by remember { mutableStateOf(false) }

    // Validation errors
    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        GradientDarkEnd,
                        GradientDarkStart,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Header
            Text(text = "⚡", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Create Account",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Join Swastik to explore electronics near you",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Name
            SwastikTextField(
                value = name,
                onValueChange = { name = it; nameError = false },
                label = "Full Name",
                leadingIcon = Icons.Outlined.Person,
                isError = nameError,
                errorMessage = if (nameError) "Name is required" else ""
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Email
            SwastikTextField(
                value = email,
                onValueChange = { email = it; emailError = false },
                label = "Email Address",
                leadingIcon = Icons.Outlined.Email,
                keyboardType = KeyboardType.Email,
                isError = emailError,
                errorMessage = if (emailError) "Email is required" else ""
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Phone
            SwastikTextField(
                value = phone,
                onValueChange = { phone = it; phoneError = false },
                label = "Phone Number",
                leadingIcon = Icons.Outlined.Phone,
                keyboardType = KeyboardType.Phone,
                isError = phoneError,
                errorMessage = if (phoneError) "Phone number is required" else ""
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Password
            SwastikTextField(
                value = password,
                onValueChange = { password = it; passwordError = false },
                label = "Password",
                leadingIcon = Icons.Outlined.Lock,
                keyboardType = KeyboardType.Password,
                isError = passwordError,
                errorMessage = if (passwordError) "Password must be at least 6 characters" else "",
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Toggle password",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Confirm Password
            SwastikTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it; confirmError = false },
                label = "Confirm Password",
                leadingIcon = Icons.Outlined.Lock,
                keyboardType = KeyboardType.Password,
                isError = confirmError,
                errorMessage = if (confirmError) "Passwords do not match" else "",
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Terms checkbox
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = agreeToTerms,
                    onCheckedChange = { agreeToTerms = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = NexiiRed
                    )
                )
                Text(
                    text = "I agree to the Terms of Service & Privacy Policy",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Register button
            SwastikButton(
                text = "Create Account",
                onClick = {
                    nameError = name.isBlank()
                    emailError = email.isBlank()
                    phoneError = phone.isBlank()
                    passwordError = password.length < 6
                    confirmError = password != confirmPassword

                    if (!nameError && !emailError && !phoneError && !passwordError && !confirmError && agreeToTerms) {
                        onRegister(name, email, phone)
                    }
                },
                enabled = agreeToTerms,
                icon = Icons.Filled.PersonAdd
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Login link
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account? ",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Login",
                    color = NexiiRed,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.clickable(onClick = onBackToLogin)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

