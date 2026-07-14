package com.example.whatsfordinner.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.whatsfordinner.R
import com.example.whatsfordinner.ui.theme.Sage50

enum class Destinations(
    val route: String,
    val label: String,
    val icon: Int,
    val contentDescription: String
) {
    RECIPES(
        "recipes",
        "Recipes",
        R.drawable.menu_book_black,
        "Recipes"
    ),
    FAVORITES(
        "favorites",
        "Favorites",
        R.drawable.favorite_black,
        "Favorites"
    ),
    MEAL_PLAN(
        "meal_plan",
        "Meal plan",
        R.drawable.meal_plan_black,
        "Meal plan"
    )

}

@Composable
fun BottomNavBar(navController: NavController) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Destinations.entries.forEach { destination ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.background,
                    selectedTextColor = MaterialTheme.colorScheme.background,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = Color.Transparent
                ),
                selected = currentDestination?.hierarchy?.any {
                    it.route == destination.route
                } == true,
                onClick = {
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.icon),
                        contentDescription = destination.contentDescription,
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp)
                            .padding(top = 10.dp)
                    )
                },
                label = { Text(destination.label) }
            )
        }
    }
}