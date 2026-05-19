package com.swastik.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.swastik.app.data.UserPreferences
import com.swastik.app.ui.screens.*
import com.swastik.app.ui.screens.buyer.*
import com.swastik.app.ui.screens.seller.*
import kotlinx.coroutines.launch

@Composable
fun SwastikNavGraph(
    navController: NavHostController,
    userPreferences: UserPreferences,
    isLoggedIn: Boolean,
    userRole: String,
    isSellerRegistered: Boolean
) {
    val scope = rememberCoroutineScope()

    // Determine start destination based on login state
    val startDestination = if (isLoggedIn && userRole.isNotEmpty()) {
        when (userRole) {
            "buyer" -> Screen.BuyerHome.route
            "seller" -> if (isSellerRegistered) Screen.SellerDashboard.route else Screen.SellerRegistration.route
            else -> Screen.Splash.route
        }
    } else {
        Screen.Splash.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // ── Common Screens ──
        composable(Screen.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onGetStarted = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLogin = { email, name ->
                    scope.launch {
                        userPreferences.saveLogin(
                            name = name,
                            email = email,
                            phone = "",
                            role = ""
                        )
                    }
                    navController.navigate(Screen.RoleSelection.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onRegister = { name, email, phone ->
                    scope.launch {
                        userPreferences.saveLogin(
                            name = name,
                            email = email,
                            phone = phone,
                            role = ""
                        )
                    }
                    navController.navigate(Screen.RoleSelection.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.RoleSelection.route) {
            RoleSelectionScreen(
                onBuyerSelected = {
                    scope.launch {
                        userPreferences.updateRole("buyer")
                    }
                    navController.navigate(Screen.BuyerHome.route) {
                        popUpTo(Screen.RoleSelection.route) { inclusive = true }
                    }
                },
                onSellerSelected = {
                    scope.launch {
                        userPreferences.updateRole("seller")
                    }
                    if (isSellerRegistered) {
                        navController.navigate(Screen.SellerDashboard.route) {
                            popUpTo(Screen.RoleSelection.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Screen.SellerRegistration.route) {
                            popUpTo(Screen.RoleSelection.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        // ── Buyer Screens ──
        composable(Screen.BuyerHome.route) {
            BuyerHomeScreen(
                userPreferences = userPreferences,
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                },
                onSearchClick = {
                    navController.navigate(Screen.Search.route)
                },
                onShopClick = { shopId -> 
                    navController.navigate(Screen.ShopDetail.createRoute(shopId))
                },
                onCategoryClick = { categoryId ->

                    navController.navigate(Screen.CategoryProducts.createRoute(categoryId))
                },
                onNotificationsClick = {
                    navController.navigate(Screen.Notifications.route)
                },
                onSeeAllProducts = {
                    navController.navigate(Screen.AllProducts.createRoute("Trending Products"))
                },
                onSeeAllShops = {
                    navController.navigate(Screen.AllShops.route)
                },
                onChatbotClick = {
                    navController.navigate(Screen.Chatbot.route)
                }
            )
        }

        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(
                productId = productId,
                onBack = { navController.popBackStack() },
                onAddToCart = {
                    navController.navigate(Screen.Cart.route)
                },
                onBuyNow = {
                    navController.navigate(Screen.Checkout.route)
                }
            )
        }

        composable(
            route = Screen.ShopDetail.route,

            arguments = listOf(navArgument("shopId") { type = NavType.StringType })
        ) { backStackEntry ->
            val shopId = backStackEntry.arguments?.getString("shopId") ?: ""
            ShopDetailScreen(
                shopId = shopId,
                onBack = { navController.popBackStack() },
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }

        composable(Screen.Search.route) {
            SearchScreen(
                onBack = { navController.popBackStack() },
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }

        composable(Screen.Cart.route) {
            CartScreen(
                onBack = { navController.popBackStack() },
                onCheckout = {
                    navController.navigate(Screen.Checkout.route)
                }
            )
        }

        composable(Screen.Wishlist.route) {
            WishlistScreen(
                onBack = { navController.popBackStack() },
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                userPreferences = userPreferences,
                onOrderHistory = {
                    navController.navigate(Screen.OrderHistory.route)
                },
                onBack = { navController.popBackStack() },
                onLogout = {
                    scope.launch {
                        userPreferences.clearLogin()
                    }
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onEditProfile = {
                    navController.navigate(Screen.EditProfile.route)
                },
                onCustomerSupport = {
                    navController.navigate("customer_support")
                },
                onManageAddresses = {
                    navController.navigate(Screen.ManageAddress.route)
                },
                onPaymentMethods = {
                    navController.navigate(Screen.PaymentMethods.route)
                },
                onNotifications = {
                    navController.navigate(Screen.Notifications.route)
                },
                onAbout = {
                    navController.navigate(Screen.About.route)
                },
                onThemeToggle = { isDark ->
                    scope.launch {
                        userPreferences.setDarkTheme(isDark)
                    }
                }
            )
        }

        composable(Screen.EditProfile.route) {
            EditProfileScreen(
                onBack = { navController.popBackStack() },
                onSave = { navController.popBackStack() }
            )
        }

        composable(Screen.Chatbot.route) {
            ChatbotScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.OrderHistory.route) {
            OrderHistoryScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.ManageAddress.route) {
            AddressScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.PaymentMethods.route) {
            PaymentMethodsScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Notifications.route) {
            NotificationScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.About.route) {
            AboutScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Chatbot.route) {
            ChatbotScreen(onBack = { navController.popBackStack() })
        }

        composable("customer_support") {
            com.swastik.app.ui.screens.buyer.CustomerSupportScreen(onBack = { navController.popBackStack() })
        }
        
        // ── New Grids ──
        composable(Screen.CategoryProducts.route) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            val category = com.swastik.app.data.SampleData.categories.find { it.id == categoryId }
            val catName = category?.name ?: "Category"
            val products = com.swastik.app.data.SampleData.products.filter { it.category == catName }
            
            com.swastik.app.ui.screens.buyer.ProductGridScreen(
                title = "$catName Products",
                products = products,
                onBack = { navController.popBackStack() },
                onProductClick = { pid -> navController.navigate(Screen.ProductDetail.createRoute(pid)) }
            )
        }

        composable(Screen.AllProducts.route) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "All Products"
            val products = com.swastik.app.data.SampleData.products
            com.swastik.app.ui.screens.buyer.ProductGridScreen(
                title = title,
                products = products,
                onBack = { navController.popBackStack() },
                onProductClick = { pid -> navController.navigate(Screen.ProductDetail.createRoute(pid)) }
            )
        }

        composable(Screen.AllShops.route) {
            val shops = com.swastik.app.data.SampleData.shops
            com.swastik.app.ui.screens.buyer.ShopListScreen(
                title = "All Shops",
                shops = shops,
                onBack = { navController.popBackStack() },
                onShopClick = { sid -> navController.navigate(Screen.ShopDetail.createRoute(sid)) }
            )
        }

        composable(Screen.Checkout.route) {
            CheckoutScreen(
                onBack = { navController.popBackStack() },
                onPlaceOrder = {
                    navController.navigate(Screen.OrderHistory.route) {
                        popUpTo(Screen.BuyerHome.route)
                    }
                }
            )
        }

        // ── Seller Screens ──
        composable(Screen.SellerRegistration.route) {
            SellerRegistrationScreen(
                onSubmit = {
                    scope.launch {
                        userPreferences.setSellerRegistered(true)
                    }
                    navController.navigate(Screen.SellerDashboard.route) {
                        popUpTo(Screen.SellerRegistration.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() },
                onNavigateToLogin = {
                    navController.navigate("seller_login")
                }
            )
        }

        composable("seller_login") {
            SellerLoginScreen(
                onLoginSuccess = {
                    scope.launch { userPreferences.setSellerRegistered(true) }
                    navController.navigate(Screen.SellerDashboard.route) {
                        popUpTo(Screen.RoleSelection.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.SellerDashboard.route) {
            SellerDashboardScreen(
                onAddProduct = {
                    navController.navigate(Screen.AddProduct.route)
                },
                onManageProducts = {
                    navController.navigate(Screen.ManageProducts.route)
                },
                onSales = {
                    navController.navigate(Screen.SellerSales.route)
                },
                onOrders = {
                    navController.navigate(Screen.SellerOrders.route)
                },
                onSettings = {
                    navController.navigate(Screen.SellerSettings.route)
                },
                onProfile = {
                    navController.navigate(Screen.SellerProfile.route)
                },
                onStockOverview = {
                    navController.navigate("stock_overview")
                }
            )
        }

        composable("stock_overview") {
            com.swastik.app.ui.screens.seller.StockOverviewScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.SellerSales.route) {
            SellerSalesScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.SellerOrders.route) {
            SellerOrdersScreen(
                onBack = { navController.popBackStack() },
                onOrderClick = { orderId ->
                    navController.navigate(Screen.OrderDetails.createRoute(orderId))
                }
            )
        }
        
        composable(
            route = Screen.OrderDetails.route,
            arguments = listOf(navArgument("orderId") { type = NavType.StringType })
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
            OrderDetailsScreen(
                orderId = orderId,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.SellerSettings.route) {
            SellerSettingsScreen(
                onBack = { navController.popBackStack() },
                onLogout = {
                    scope.launch { userPreferences.clearLogin() }
                    navController.navigate(Screen.Login.route) { popUpTo(0) { inclusive = true } }
                }
            )
        }

        composable(Screen.SellerProfile.route) {
            SellerProfileScreen(
                userPreferences = userPreferences,
                onBack = { navController.popBackStack() },
                onSettings = { navController.navigate(Screen.SellerSettings.route) },
                onEditBusiness = { navController.navigate("edit_business_profile") },
                onShopDetails = { navController.navigate("seller_shop_details") },
                onLogout = {
                    scope.launch { userPreferences.clearLogin() }
                    navController.navigate(Screen.Login.route) { popUpTo(0) { inclusive = true } }
                },
                onThemeToggle = { isDark ->

                    scope.launch { userPreferences.setDarkTheme(isDark) }
                }
            )
        }

        composable("edit_business_profile") {
            com.swastik.app.ui.screens.seller.EditBusinessProfileScreen(onBack = { navController.popBackStack() })
        }

        composable("seller_shop_details") {
            com.swastik.app.ui.screens.seller.SellerShopDetailsScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.AddProduct.route) {
            AddProductScreen(
                onBack = { navController.popBackStack() },
                onSave = { navController.popBackStack() }
            )
        }

        composable(Screen.ManageProducts.route) {
            ManageProductsScreen(
                onBack = { navController.popBackStack() },
                onEditProduct = { }
            )
        }
    }
}
