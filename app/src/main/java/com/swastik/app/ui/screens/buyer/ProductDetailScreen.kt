package com.swastik.app.ui.screens.buyer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.swastik.app.data.AppStateManager
import com.swastik.app.data.SampleData
import com.swastik.app.ui.components.CustomRatingBar
import com.swastik.app.ui.components.SwastikButton
import com.swastik.app.ui.components.SwastikOutlinedButton
import com.swastik.app.ui.theme.*
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: String,
    onBack: () -> Unit,
    onAddToCart: () -> Unit,
    onBuyNow: () -> Unit
) {
    val product = SampleData.products.find { it.id == productId } ?: return
    val formatter = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    var isFavorite by remember { mutableStateOf(AppStateManager.isFavorite(productId)) }
    val snackbarHostState = remember { SnackbarHostState() }
    var showAddedToCartSnackbar by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(showAddedToCartSnackbar) {
        if (showAddedToCartSnackbar) {
            snackbarHostState.showSnackbar("✅ Added to cart!")
            showAddedToCartSnackbar = false
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { 
                        Toast.makeText(context, "Share clicked", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Outlined.Share, "Share")
                    }
                    IconButton(onClick = {
                        AppStateManager.toggleFavorite(productId)
                        isFavorite = !isFavorite
                    }) {
                        Icon(
                            if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            "Favorite",
                            tint = if (isFavorite) NexiiRed else MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            // Bottom Action Bar
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SwastikOutlinedButton(
                        text = "Add to Cart",
                        onClick = {
                            AppStateManager.addToCart(product)
                            showAddedToCartSnackbar = true
                        },
                        modifier = Modifier.weight(1f),
                        icon = Icons.Filled.ShoppingCart
                    )
                    SwastikButton(
                        text = "Buy Now",
                        onClick = {
                            AppStateManager.addToCart(product)
                            onBuyNow()
                        },
                        modifier = Modifier.weight(1f),
                        icon = Icons.Filled.ShoppingBag
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
        ) {
            // ── Image Carousel ──
            val pagerState = rememberPagerState(pageCount = { product.imageUrls.size })

            Box {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) { page ->
                    SubcomposeAsyncImage(
                        model = product.imageUrls[page],
                        contentDescription = "${product.name} image ${page + 1}",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        loading = {
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(color = NexiiRed)
                            }
                        },
                        error = {
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceVariant)) {
                                Icon(Icons.Filled.BrokenImage, "Error", tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(48.dp))
                            }
                        }
                    )
                }

                // Image count indicator
                Surface(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.Black.copy(alpha = 0.6f)
                ) {
                    Text(
                        text = "${pagerState.currentPage + 1}/${product.imageUrls.size}",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }

                // Page indicators
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(product.imageUrls.size) { i ->
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .size(if (pagerState.currentPage == i) 20.dp else 6.dp, 6.dp)
                                .clip(CircleShape)
                                .background(
                                    if (pagerState.currentPage == i) NexiiRed
                                    else Color.White.copy(alpha = 0.5f)
                                )
                        )
                    }
                }
            }

            // ── Product Info ──
            Column(modifier = Modifier.padding(16.dp)) {
                // Brand
                Text(
                    text = product.brand,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Name
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Rating row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = SuccessGreen
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "${product.rating}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            Icon(
                                Icons.Filled.Star,
                                "Rating",
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                    Text(
                        text = "${product.reviewCount} Ratings",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Price section
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = formatter.format(product.sellingPrice),
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (product.mrp != product.sellingPrice) {
                        Text(
                            text = formatter.format(product.mrp),
                            style = MaterialTheme.typography.titleMedium,
                            textDecoration = TextDecoration.LineThrough,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = NexiiRed
                        ) {
                            Text(
                                text = "${product.discountPercent}% OFF",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Text(
                    text = "inclusive of all taxes",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(4.dp))

                // EMI info
                Text(
                    text = "EMI starts at ₹${(product.sellingPrice / 12).toInt()}/month",
                    style = MaterialTheme.typography.bodySmall,
                    color = InfoBlue,
                    fontWeight = FontWeight.Medium
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                // ── Shop Info ──
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Storefront,
                        "Shop",
                        tint = NexiiRed,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = product.shopName,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Verified Seller • ${SampleData.shops.find { it.id == product.shopId }?.distance ?: 0} km away",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Icon(
                        Icons.Filled.Verified,
                        "Verified",
                        tint = SuccessGreen,
                        modifier = Modifier.size(20.dp)
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                // ── Delivery Info ──
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(Icons.Filled.LocalShipping, "Delivery", tint = SuccessGreen)
                    Column {
                        Text("Free Delivery", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium)
                        Text("Delivery by 28 Mar, Friday", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(Icons.Filled.Replay, "Return", tint = InfoBlue)
                    Column {
                        Text("7 Day Return Policy", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium)
                        Text("Easy returns within 7 days", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(Icons.Filled.VerifiedUser, "Warranty", tint = WarningAmber)
                    Column {
                        Text(product.warranty, fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium)
                        Text("Manufacturer warranty", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                // ── Specifications ──
                Text(
                    text = "Specifications",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                product.specifications.forEach { (key, value) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        Text(
                            text = key,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = value,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1.5f)
                        )
                    }
                    HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                // ── Description ──
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 22.sp
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                // ── Manufacturer Details ──
                Text(
                    text = "Manufacturer",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.manufacturerDetails,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ── Customer Rating Section ──
                Text(
                    text = "Customer Ratings",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${product.rating}",
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = SuccessGreen
                        )
                        CustomRatingBar(rating = product.rating, starSize = 20.dp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${product.reviewCount} reviews",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        RatingBar("5 ★", 0.7f)
                        RatingBar("4 ★", 0.2f)
                        RatingBar("3 ★", 0.05f)
                        RatingBar("2 ★", 0.03f)
                        RatingBar("1 ★", 0.02f)
                    }
                }

                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
private fun RatingBar(label: String, fraction: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.width(28.dp)
        )
        LinearProgressIndicator(
            progress = { fraction },
            modifier = Modifier
                .weight(1f)
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = when {
                label.startsWith("5") || label.startsWith("4") -> SuccessGreen
                label.startsWith("3") -> WarningAmber
                else -> NexiiRed
            },
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}
