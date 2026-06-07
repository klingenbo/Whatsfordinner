package com.example.whatsfordinner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.whatsfordinner.components.BottomNavBar
import com.example.whatsfordinner.onboarding.Onboarding1
import com.example.whatsfordinner.onboarding.Onboarding2
import com.example.whatsfordinner.onboarding.Onboarding3
import com.example.whatsfordinner.screens.FavoritesScreen
import com.example.whatsfordinner.screens.MealPlanScreen
import com.example.whatsfordinner.screens.RecipeDetails
import com.example.whatsfordinner.screens.RecipeListScreen
import com.example.whatsfordinner.ui.theme.WhatsForDinnerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatsForDinnerTheme {
                val viewModel: RecipesListViewModel = viewModel()
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStack?.destination?.route

                // Screen where bottom bar will be shown
                val showBottomBar = currentRoute in listOf("recipes", "favorites", "meal_plan")

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "onboarding1",
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable("onboarding1") {
                            Onboarding1(
                                onContinueClicked = {
                                    navController.navigate("onboarding2")
                                }
                            )
                        }

                        composable("onboarding2") {
                            Onboarding2(
                                onContinueClicked = {
                                    navController.navigate("onboarding3")
                                }
                            )
                        }

                        composable("onboarding3") {
                            Onboarding3(
                                onGetStartedClicked = {
                                    navController.navigate("recipes")
                                }
                            )
                        }

                        composable("recipes") {

                            RecipeListScreen(
                                viewModel = viewModel,
                                onDetailsClicked = { recipe ->
                                    viewModel.setSelectedRecipe(recipe)
                                    navController.navigate("recipeDetails")
                                }
                            )
                        }

                        composable("favorites") { FavoritesScreen() }
                        composable("meal_plan") { MealPlanScreen() }

                        composable("recipeDetails") {

                            RecipeDetails(
                                viewModel = viewModel,
                                onBackClicked = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
    }