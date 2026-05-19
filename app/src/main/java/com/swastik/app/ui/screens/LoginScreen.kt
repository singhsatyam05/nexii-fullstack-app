package com.swastik.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swastik.app.ui.components.SwastikButton
import com.swastik.app.ui.components.SwastikTextField
import com.swastik.app.ui.theme.*

@Composable
fun LoginScreen(
    onLogin: (email: String, name: String) -> Unit,
    onRegister: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var showForgotDialog by remember { mutableStateOf(false) }
    var forgotEmail by remember { mutableStateOf("") }
    var showForgotSuccess by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    // Forgot Password Dialog
    if (showForgotDialog) {
        AlertDialog(
            onDismissRequest = { showForgotDialog = false },
            icon = { Text("🔒", fontSize = 32.sp) },
            title = { Text("Reset Password", fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text(
                        "Enter your email address and we'll send you a password reset link.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    SwastikTextField(
                        value = forgotEmail,
                        onValueChange = { forgotEmail = it },
                        label = "Email Address",
                        leadingIcon = Icons.Outlined.Email,
                        keyboardType = KeyboardType.Email
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showForgotDialog = false
                        showForgotSuccess = true
                    }
                ) {
                    Text("Send Reset Link", color = NexiiRed, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showForgotDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showForgotSuccess) {
        AlertDialog(
            onDismissRequest = { showForgotSuccess = false },
            icon = { Text("✅", fontSize = 32.sp) },
            title = { Text("Email Sent!", fontWeight = FontWeight.Bold) },
            text = {
                Text(
                    "If an account exists with that email, you'll receive a password reset link shortly.",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(onClick = { showForgotSuccess = false }) {
                    Text("OK", color = NexiiRed, fontWeight = FontWeight.Bold)
                }
            }
        )
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
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
                Spacer(modifier = Modifier.height(60.dp))

                // Logo
                Text(text = "⚡", fontSize = 56.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "SWASTIK",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = NexiiRed,
                    letterSpacing = 4.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Email field
                SwastikTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = false
                    },
                    label = "Email or Phone",
                    leadingIcon = Icons.Outlined.Email,
                    keyboardType = KeyboardType.Email,
                    isError = emailError,
                    errorMessage = if (emailError) "Please enter your email" else ""
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password field
                SwastikTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = false
                    },
                    label = "Password",
                    leadingIcon = Icons.Outlined.Lock,
                    keyboardType = KeyboardType.Password,
                    isError = passwordError,
                    errorMessage = if (passwordError) "Please enter your password" else "",
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

                // Forgot password
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { showForgotDialog = true }) {
                        Text(
                            text = "Forgot Password?",
                            color = NexiiRed,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Login button
                SwastikButton(
                    text = "Login",
                    onClick = {
                        emailError = email.isBlank()
                        passwordError = password.isBlank()
                        if (!emailError && !passwordError) {
                            val displayName = email.substringBefore("@").replaceFirstChar { it.uppercase() }
                            onLogin(email, displayName)
                        }
                    },
                    icon = Icons.Filled.Login
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Or divider
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                    Text(
                        text = "  OR  ",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelSmall
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Social login buttons
                OutlinedButton(
                    onClick = {
                        onLogin("demo@google.com", "Google User")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(text = "🔵  Continue with Google", fontWeight = FontWeight.Medium)
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {
                        onLogin("demo@phone.com", "Phone User")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(text = "📱  Continue with Phone OTP", fontWeight = FontWeight.Medium)
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Register link
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Don't have an account? ",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Register",
                        color = NexiiRed,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable(onClick = onRegister)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

