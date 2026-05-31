package com.example.whatsfordinner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whatsfordinner.ui.theme.WhatsForDinnerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatsForDinnerTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "onboarding1"
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
                        Onboarding3()
                    }
                }
            }
        }
    }
}