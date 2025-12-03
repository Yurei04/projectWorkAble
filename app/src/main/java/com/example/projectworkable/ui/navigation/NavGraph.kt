package com.example.projectworkable.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectworkable.ui.screens.HeroScreen
import com.example.projectworkable.ui.screens.JobsScreen
import com.example.projectworkable.ui.screens.ProfileScreen
import com.example.projectworkable.ui.screens.ResourceHubScreen
import com.example.projectworkable.ui.screens.BlogScreen
import com.example.projectworkable.ui.screens.BlogDetailScreen
import com.example.projectworkable.ui.screens.JobDetailScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Jobs : Screen("jobs")
    object Profile : Screen("profile")
    object Resource : Screen("resource")
    object Blog : Screen("blog")

    object BlogDetail : Screen("blog/{blogId}") {
        fun createRoute(blogId: Int) = "blog/$blogId"
    }
}

@Composable
fun WorkableNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HeroScreen() }
        composable("jobs") {
            JobsScreen(
                onOpenJob = { jobId ->
                    navController.navigate("jobDetail/$jobId")
                }
            )
        }

        composable("jobDetail/{jobId}") { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId")!!.toInt()
            JobDetailScreen(
                jobId = jobId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Profile.route) { ProfileScreen() }
        composable(Screen.Resource.route) { ResourceHubScreen() }

        // Blog Screen - Passes navigation action down
        composable(Screen.Blog.route) {
            BlogScreen(
                onOpenPost = { blogId ->
                    navController.navigate(Screen.BlogDetail.createRoute(blogId))
                }
            )
        }

        // Blog Detail Screen - Receives the argument and the back action
        composable(
            route = Screen.BlogDetail.route,
            arguments = listOf(navArgument("blogId") { type = NavType.IntType })
        ) { backStackEntry ->
            val blogId = backStackEntry.arguments?.getInt("blogId")

            if (blogId != null) {
                BlogDetailScreen(
                    blogId = blogId,
                    // 💡 Pass the action to navigate back (close the screen)
                    onBackClick = { navController.navigateUp() }
                )
            }
        }
    }
}