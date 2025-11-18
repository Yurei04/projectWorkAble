package com.example.projectworkable.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectworkable.ui.screens.HeroScreen
import com.example.projectworkable.ui.screens.JobsScreen
import com.example.projectworkable.ui.screens.ProfileScreen
import com.example.projectworkable.ui.screens.ResourceHubScreen
import com.example.projectworkable.ui.screens.BlogScreen




// Simple sealed class for routes — helpful and type-safe
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Jobs : Screen("jobs")
    object Profile : Screen("profile")

    object Resource : Screen("resource")

    object Blog : Screen("blog")
}

// Root Graph composable. Use this from MainActivity.
@Composable
fun WorkableNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HeroScreen()
        }
        composable(Screen.Jobs.route) {
            JobsScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
        composable(Screen.Resource.route) {
            ResourceHubScreen()
        }
        composable(Screen.Blog.route) {
            BlogScreen()
        }
    }
}
