package com.swastik.app.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swastik.app.ui.components.SwastikButton
import com.swastik.app.ui.theme.*
import kotlinx.coroutines.launch

data class OnboardingPage(
    val emoji: String,
    val title: String,
    val description: String
)

val onboardingPages = listOf(
    OnboardingPage(
        emoji = "📍",
        title = "Discover Nearby Shops",
        description = "Find top-rated electronics shops within 30-40 km of your location. Browse from verified sellers in your neighborhood."
    ),
    OnboardingPage(
        emoji = "🔍",
        title = "Compare & Choose",
        description = "Compare prices, specifications, and reviews across multiple shops. Get the best deals on smartphones, laptops, TVs, and more."
    ),
    OnboardingPage(
        emoji = "🚀",
        title = "Quick Delivery",
        description = "Order online and get it delivered to your doorstep, or pick it up directly from the store. Fast, reliable, local."
    )
)

@Composable
fun OnboardingScreen(
    onGetStarted: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        GradientDarkEnd,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Skip button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, end = 24.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onGetStarted) {
                    Text(
                        text = "Skip",
                        color = NexiiRed,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // Pager
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                val currentPage = onboardingPages[page]
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currentPage.emoji,
                        fontSize = 80.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = currentPage.title,
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = currentPage.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp
                    )
                }
            }

            // Dot indicators
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(onboardingPages.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    val color by animateColorAsState(
                        targetValue = if (isSelected) NexiiRed else MaterialTheme.colorScheme.surfaceVariant,
                        label = "dot"
                    )
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (isSelected) 28.dp else 8.dp, 8.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }

            // Bottom buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (pagerState.currentPage == onboardingPages.size - 1) {
                    SwastikButton(
                        text = "Get Started",
                        onClick = onGetStarted
                    )
                } else {
                    SwastikButton(
                        text = "Next",
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    )
                }
            }
        }
    }
}

