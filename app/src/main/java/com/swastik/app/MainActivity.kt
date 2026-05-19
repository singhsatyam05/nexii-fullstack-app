package com.swastik.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.swastik.app.data.UserPreferences
import com.swastik.app.navigation.Screen
import com.swastik.app.navigation.SwastikNavGraph
import com.swastik.app.ui.components.NexiiBottomNavBar
import com.swastik.app.ui.theme.SwastikTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userPreferences = UserPreferences(this)
        enableEdgeToEdge()
        setContent {
            val isDarkTheme by userPreferences.isDarkTheme.collectAsStateWithLifecycle(
                initialValue = androidx.compose.foundation.isSystemInDarkTheme()
            )
            SwastikTheme(darkTheme = isDarkTheme) {
                SwastikApp(userPreferences = userPreferences)
            }
        }
    }
}

@Composable
fun SwastikApp(userPreferences: UserPreferences) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isLoggedIn by userPreferences.isLoggedIn.collectAsState(initialValue = null)
    val userRole by userPreferences.userRole.collectAsState(initialValue = null)
    val isSellerRegistered by userPreferences.isSellerRegistered.collectAsState(initialValue = null)

    if (isLoggedIn == null || userRole == null || isSellerRegistered == null) {
        com.swastik.app.ui.screens.SplashScreen(onSplashFinished = {})
        return
    }

    // Now safely unwrapped
    val loggedIn = isLoggedIn == true
    val role = userRole ?: ""
    val sellerRegistered = isSellerRegistered == true

    // Show bottom nav only on main buyer screens
    val showBottomNav = currentRoute in listOf(
        Screen.BuyerHome.route,
        Screen.Wishlist.route,
        Screen.Cart.route,
        Screen.Profile.route
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomNav) {
                NexiiBottomNavBar(
                    currentRoute = currentRoute ?: Screen.BuyerHome.route,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            // Pop up to the start destination to avoid building up a large stack
                            popUpTo(Screen.BuyerHome.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        val modifier = if (showBottomNav) Modifier.padding(innerPadding).consumeWindowInsets(innerPadding) else Modifier
        Surface(modifier = modifier) {
            SwastikNavGraph(
                navController = navController,
                userPreferences = userPreferences,
                isLoggedIn = loggedIn,
                userRole = role,
                isSellerRegistered = sellerRegistered
            )
        }
    }
}
