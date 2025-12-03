package com.example.projectworkable.ui.navigation

import android.net.Uri
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
import com.example.projectworkable.ui.screens.JobApplicationScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Jobs : Screen("jobs")
    object Profile : Screen("profile")
    object Resource : Screen("resource")
    object Blog : Screen("blog")

    object BlogDetail : Screen("blog/{blogId}") {
        fun createRoute(blogId: Int) = "blog/$blogId"
    }

    object JobDetail : Screen("jobDetail/{jobId}") {
        fun createRoute(jobId: Int) = "jobDetail/$jobId"
    }

    object JobApplication : Screen("job_application/{jobTitle}") {
        fun createRoute(jobTitle: String) = "job_application/${Uri.encode(jobTitle)}"
    }
}

@Composable
fun WorkableNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        // Home Screen
        composable(Screen.Home.route) {
            HeroScreen()
        }

        // Jobs List Screen
        composable(Screen.Jobs.route) {
            JobsScreen(
                onOpenJob = { jobId ->
                    navController.navigate(Screen.JobDetail.createRoute(jobId))
                }
            )
        }

        // Job Detail Screen
        composable(
            route = Screen.JobDetail.route,
            arguments = listOf(navArgument("jobId") { type = NavType.IntType })
        ) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getInt("jobId") ?: 0
            JobDetailScreen(
                jobId = jobId,
                onBackClick = { navController.popBackStack() },
                onApplyClick = { jobTitle ->
                    navController.navigate(Screen.JobApplication.createRoute(jobTitle))
                }
            )
        }

        // Job Application Screen
        composable(
            route = Screen.JobApplication.route,
            arguments = listOf(navArgument("jobTitle") { type = NavType.StringType })
        ) { backStackEntry ->
            val jobTitle = backStackEntry.arguments?.getString("jobTitle") ?: "Position"
            JobApplicationScreen(
                jobTitle = Uri.decode(jobTitle),
                onBackClick = {
                    navController.popBackStack()
                },
                onSubmitSuccess = {
                    // Navigate back to jobs list after successful submission
                    navController.popBackStack(Screen.Jobs.route, inclusive = false)
                }
            )
        }

        // Profile Screen
        composable(Screen.Profile.route) {
            ProfileScreen()
        }

        // Resource Hub Screen
        composable(Screen.Resource.route) {
            ResourceHubScreen()
        }

        // Blog List Screen
        composable(Screen.Blog.route) {
            BlogScreen(
                onOpenPost = { blogId ->
                    navController.navigate(Screen.BlogDetail.createRoute(blogId))
                }
            )
        }

        // Blog Detail Screen
        composable(
            route = Screen.BlogDetail.route,
            arguments = listOf(navArgument("blogId") { type = NavType.IntType })
        ) { backStackEntry ->
            val blogId = backStackEntry.arguments?.getInt("blogId")

            if (blogId != null) {
                BlogDetailScreen(
                    blogId = blogId,
                    onBackClick = { navController.navigateUp() }
                    // Note: If you want to add Apply button to blog articles too,
                    // update BlogDetailScreen.kt to accept onApplyClick parameter
                    // and uncomment the line below:
                    // , onApplyClick = { jobTitle ->
                    //     navController.navigate(Screen.JobApplication.createRoute(jobTitle))
                    // }
                )
            }
        }
    }
}