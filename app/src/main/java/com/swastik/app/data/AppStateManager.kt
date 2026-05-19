package com.swastik.app.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import com.swastik.app.data.model.CartItem
import com.swastik.app.data.model.Product

object AppStateManager {

    // ── Cart ──
    val cartItems = mutableStateListOf<CartItem>()

    fun addToCart(product: Product, quantity: Int = 1) {
        val existing = cartItems.find { it.product.id == product.id }
        if (existing != null) {
            val index = cartItems.indexOf(existing)
            cartItems[index] = existing.copy(quantity = existing.quantity + quantity)
        } else {
            cartItems.add(CartItem(product, quantity))
        }
    }

    fun removeFromCart(productId: String) {
        cartItems.removeAll { it.product.id == productId }
    }

    fun updateCartQuantity(productId: String, newQuantity: Int) {
        val index = cartItems.indexOfFirst { it.product.id == productId }
        if (index >= 0) {
            cartItems[index] = cartItems[index].copy(quantity = newQuantity)
        }
    }

    fun clearCart() {
        cartItems.clear()
    }

    fun getCartTotal(): Double {
        return cartItems.sumOf { it.product.sellingPrice * it.quantity }
    }

    // ── Favorites / Wishlist ──
    val favoriteIds = mutableStateListOf<String>()

    fun toggleFavorite(productId: String) {
        if (favoriteIds.contains(productId)) {
            favoriteIds.remove(productId)
        } else {
            favoriteIds.add(productId)
        }
    }

    fun isFavorite(productId: String): Boolean {
        return favoriteIds.contains(productId)
    }

    fun getFavoriteProducts(): List<Product> {
        return SampleData.products.filter { favoriteIds.contains(it.id) }
    }

    // ── Seller Products (for delete/manage) ──
    val sellerProducts = mutableStateListOf<Product>().apply {
        addAll(SampleData.products.take(6))
    }

    fun deleteSellerProduct(productId: String) {
        sellerProducts.removeAll { it.id == productId }
    }
}
