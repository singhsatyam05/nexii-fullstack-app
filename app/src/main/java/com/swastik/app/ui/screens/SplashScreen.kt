package com.swastik.app.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swastik.app.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(1500)
        onSplashFinished()
    }

    // Animations
    val infiniteTransition = rememberInfiniteTransition(label = "splash")

    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(1200, easing = FastOutSlowInEasing),
        label = "scale"
    )

    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(1500, easing = LinearEasing),
        label = "alpha"
    )

    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    // Progress bar animation
    var progress by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(Unit) {
        animate(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = tween(1200, easing = LinearEasing)
        ) { value, _ ->
            progress = value
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        GradientDarkEnd,
                        GradientDarkStart,
                        NexiiRedLight.copy(alpha = 0.3f)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo icon
            Text(
                text = "⚡",
                fontSize = 80.sp,
                modifier = Modifier
                    .scale(scale * pulseScale)
                    .alpha(alpha)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // App Name
            Text(
                text = "SWASTIK",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = NexiiRed,
                letterSpacing = 6.sp,
                modifier = Modifier
                    .alpha(alpha)
                    .scale(scale)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your Nearest Electronics, Delivered",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.alpha(alpha)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Progress bar
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .width(220.dp)
                    .height(4.dp)
                    .alpha(alpha),
                color = NexiiRed,
                trackColor = NexiiRedLight.copy(alpha = 0.3f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Loading amazing deals...",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                modifier = Modifier.alpha(alpha)
            )
        }

        // Bottom branding
        Text(
            text = "Hyperlocal Electronics Marketplace",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .alpha(alpha)
        )
    }
}

