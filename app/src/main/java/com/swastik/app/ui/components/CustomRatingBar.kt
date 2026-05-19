package com.swastik.app.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.swastik.app.ui.theme.StarEmpty
import com.swastik.app.ui.theme.StarFilled

@Composable
fun CustomRatingBar(
    rating: Float,
    modifier: Modifier = Modifier,
    starSize: Dp = 18.dp,
    maxStars: Int = 5
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val icon = when {
                i <= rating.toInt() -> Icons.Filled.Star
                i == rating.toInt() + 1 && rating % 1 >= 0.5f -> Icons.Filled.StarHalf
                else -> Icons.Outlined.StarOutline
            }
            val tint = if (i <= rating.toInt() || (i == rating.toInt() + 1 && rating % 1 >= 0.5f)) {
                StarFilled
            } else {
                StarEmpty
            }
            Icon(
                imageVector = icon,
                contentDescription = "Star $i",
                tint = tint,
                modifier = Modifier.size(starSize)
            )
        }
    }
}
