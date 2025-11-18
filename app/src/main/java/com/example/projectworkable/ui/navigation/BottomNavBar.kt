package com.example.projectworkable.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.projectworkable.R

// A small data holder for nav items
data class BottomNavItem(val route: String, val iconRes: Int, val label: String)

@Composable
fun BottomNavBar(navController: NavController) {
    // define your three bottom items
    val items = listOf(
        BottomNavItem(Screen.Home.route, R.drawable.ic_home, "Home"),
        BottomNavItem(Screen.Resource.route, R.drawable.ic_temporary, "Resources"),
        BottomNavItem(Screen.Jobs.route, R.drawable.ic_jobs, "Jobs"),
        BottomNavItem(Screen.Blog.route, R.drawable.ic_temporary, "Blog"),
        BottomNavItem(Screen.Profile.route, R.drawable.ic_profile, "Profile"),
    )

    // observe current route so we can highlight the selected tab
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar {
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(painter = painterResource(id = item.iconRes), contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }

}
