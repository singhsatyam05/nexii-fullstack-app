package com.swastik.app.ui.screens.buyer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swastik.app.data.SampleData
import com.swastik.app.data.UserPreferences
import com.swastik.app.ui.components.*
import com.swastik.app.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyerHomeScreen(
    userPreferences: UserPreferences,
    onProductClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onShopClick: (String) -> Unit,
    onCategoryClick: (String) -> Unit,
    onNotificationsClick: () -> Unit,
    onSeeAllProducts: () -> Unit,
    onSeeAllShops: () -> Unit,
    onChatbotClick: () -> Unit
) {
    val products = SampleData.products
    val categories = SampleData.categories
    val shops = SampleData.shops
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val scrollState = rememberScrollState()

    var showLocationSheet by remember { mutableStateOf(false) }
    val currentLocation by userPreferences.userLocation.collectAsState(initial = "Sector 14, Gurgaon")

    if (showLocationSheet) {
        ModalBottomSheet(onDismissRequest = { showLocationSheet = false }) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Select Delivery Location", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                listOf("Sector 14, Gurgaon", "Connaught Place, Delhi", "Cyber City, Gurgaon", "Noida Sector 62").forEach { loc ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                scope.launch {
                                    userPreferences.setLocation(loc)
                                }
                                showLocationSheet = false
                            }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Filled.LocationOn, contentDescription = null, tint = NexiiRed)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(loc, style = MaterialTheme.typography.bodyLarge)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = onChatbotClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape,
                modifier = Modifier.offset(y = 12.dp)
            ) {
                Icon(Icons.Filled.SmartToy, contentDescription = "Nexi Chatbot", modifier = Modifier.size(20.dp))
            }
        }
    ) { innerPadding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // ── Top App Bar ──
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                // Location + Notification row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.clickable { showLocationSheet = true },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "Location",
                            tint = NexiiRed,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Column {
                            Text(
                                text = "Deliver to",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = currentLocation,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowDown,
                                    contentDescription = "Change",
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                    IconButton(onClick = onNotificationsClick) {
                        BadgedBox(
                            badge = {
                                Badge(containerColor = ErrorRed) {
                                    Text("3", color = Color.White, fontSize = 10.sp)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = "Notifications"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Search bar
                SwastikSearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = onSearchClick,
                    onFilterClick = { 
                        Toast.makeText(context, "Filters clicked", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        // ── Scrollable Content ──
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // ── Banner Carousel ──
            val bannerPagerState = rememberPagerState(pageCount = { 3 })
            val banners = listOf(
                Triple("🤖 NEXII AI Picks", "Personalized laptops and gear", GradientRedStart),
                Triple("🔥 Cyber Sale", "Up to 70% OFF on Audio", GradientDarkStart),
                Triple("📱 New Arrivals", "iPhone 15 Series Now Available", InfoBlue)
            )

            HorizontalPager(
                state = bannerPagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(horizontal = 16.dp),
                pageSpacing = 12.dp
            ) { page ->
                Card(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = banners[page].third)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        banners[page].third,
                                        banners[page].third.copy(alpha = 0.7f)
                                    )
                                )
                            )
                            .padding(20.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Column {
                            Text(
                                text = banners[page].first,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = banners[page].second,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.White,
                                modifier = Modifier.clickable {
                                    Toast.makeText(context, "Shop Now clicked", Toast.LENGTH_SHORT).show()
                                }
                            ) {
                                Text(
                                    text = "Shop Now →",
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                                    fontWeight = FontWeight.Bold,
                                    color = if (page == 1) Color.White else banners[page].third,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            }

            // Banner dots
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(3) { i ->
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .size(if (bannerPagerState.currentPage == i) 20.dp else 6.dp, 6.dp)
                            .clip(CircleShape)
                            .background(
                                if (bannerPagerState.currentPage == i) NexiiRed
                                else MaterialTheme.colorScheme.surfaceVariant
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── Categories ──
            SectionHeader(title = "Categories", onSeeAll = onSeeAllProducts)
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { cat ->
                    CategoryChip(
                        emoji = cat.icon,
                        name = cat.name,
                        isSelected = false,
                        onClick = {
                            onCategoryClick(cat.id)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── Nearby Shops ──
            SectionHeader(title = "Nearby Shops", onSeeAll = onSeeAllShops)
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(shops) { shop ->
                    ShopCard(
                        shop = shop,
                        onClick = { onShopClick(shop.id) },
                        modifier = Modifier.width(280.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── Deals of the Day ──
            SectionHeader(title = "⚡ Deals of the Day", onSeeAll = onSeeAllProducts)
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(products.filter { it.discountPercent >= 15 }) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product.id) },
                        onFavoriteClick = { },
                        modifier = Modifier.width(200.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── All Products Grid ──
            SectionHeader(title = "Trending Products", onSeeAll = onSeeAllProducts)
            Spacer(modifier = Modifier.height(8.dp))

            // Inline grid (not nested LazyVerticalGrid)
            val filteredProducts = remember(products, selectedCategory, searchQuery) {
                products.filter { product ->
                    val matchesCategory = selectedCategory == null || product.category == categories.find { c -> c.id == selectedCategory }?.name
                    val matchesSearch = searchQuery.isBlank() || 
                        product.name.contains(searchQuery, ignoreCase = true) || 
                        product.brand.contains(searchQuery, ignoreCase = true) || 
                        product.category.contains(searchQuery, ignoreCase = true)
                    matchesCategory && matchesSearch
                }.take(6)
            }

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                filteredProducts.chunked(2).forEach { rowProducts ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowProducts.forEach { product ->
                            ProductCard(
                                product = product,
                                onClick = { onProductClick(product.id) },
                                onFavoriteClick = { },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        // If odd number, add spacer
                        if (rowProducts.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    onSeeAll: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        TextButton(onClick = onSeeAll) {
            Text(
                text = "See All",
                color = NexiiRed,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
