package com.swastik.app.navigation

sealed class Screen(val route: String) {
    // Common
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object RoleSelection : Screen("role_selection")

    // Buyer
    object BuyerHome : Screen("buyer_home")
    object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: String) = "product_detail/$productId"
    }
    object Search : Screen("search")
    object Cart : Screen("buyer_cart")
    object Wishlist : Screen("buyer_wishlist")
    object Profile : Screen("buyer_profile")
    object EditProfile : Screen("edit_profile")
    object Chatbot : Screen("chatbot")
    object OrderHistory : Screen("order_history")
    object Checkout : Screen("checkout")
    object ManageAddress : Screen("manage_address")
    object PaymentMethods : Screen("payment_methods")
    object Notifications : Screen("buyer_notifications")
    object About : Screen("about")
    object ShopDetail : Screen("shop_detail/{shopId}") {
        fun createRoute(shopId: String) = "shop_detail/$shopId"
    }

    // Seller
    object CategoryProducts : Screen("category_products/{categoryId}") {
        fun createRoute(categoryId: String) = "category_products/$categoryId"
    }
    object AllProducts : Screen("all_products/{title}") {
        fun createRoute(title: String) = "all_products/$title"
    }
    object AllShops : Screen("all_shops")

    object SellerRegistration : Screen("seller_registration")
    object SellerDashboard : Screen("seller_dashboard")
    object AddProduct : Screen("add_product")
    object ManageProducts : Screen("manage_products")
    object SellerSales : Screen("seller_sales")
    object SellerOrders : Screen("seller_orders")
    object OrderDetails : Screen("order_details/{orderId}") {
        fun createRoute(orderId: String) = "order_details/$orderId"
    }
    object SellerSettings : Screen("seller_settings")
    object SellerProfile : Screen("seller_profile")
}
