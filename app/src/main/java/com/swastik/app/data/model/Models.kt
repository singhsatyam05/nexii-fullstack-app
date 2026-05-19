package com.swastik.app.data.model

data class Product(
    val id: String,
    val name: String,
    val brand: String,
    val category: String,
    val description: String,
    val specifications: Map<String, String>,
    val mrp: Double,
    val sellingPrice: Double,
    val discountPercent: Int,
    val rating: Float,
    val reviewCount: Int,
    val imageUrls: List<String>,
    val shopId: String,
    val shopName: String,
    val inStock: Boolean = true,
    val quantity: Int = 0,
    val warranty: String = "",
    val manufacturerDetails: String = "",
    val isFavorite: Boolean = false
)

data class Category(
    val id: String,
    val name: String,
    val icon: String,
    val productCount: Int = 0
)

data class Shop(
    val id: String,
    val name: String,
    val ownerName: String,
    val address: String,
    val phone: String,
    val email: String,
    val gstin: String,
    val rating: Float,
    val reviewCount: Int,
    val distance: Double, // in km
    val isVerified: Boolean,
    val imageUrl: String,
    val categories: List<String>,
    val productCount: Int = 0
)

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val profileImageUrl: String = "",
    val isSeller: Boolean = false,
    val addresses: List<Address> = emptyList()
)

data class Address(
    val id: String,
    val label: String, // Home, Work, Other
    val fullAddress: String,
    val city: String,
    val state: String,
    val pincode: String,
    val isDefault: Boolean = false
)

data class CartItem(
    val product: Product,
    val quantity: Int = 1
)

data class Order(
    val id: String,
    val items: List<CartItem>,
    val totalAmount: Double,
    val status: OrderStatus,
    val orderDate: String,
    val deliveryDate: String = "",
    val address: Address,
    val paymentMethod: String
)

enum class OrderStatus {
    PLACED, CONFIRMED, SHIPPED, OUT_FOR_DELIVERY, DELIVERED, CANCELLED, RETURNED
}
