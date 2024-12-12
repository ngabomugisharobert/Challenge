package com.robert.challenge.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.robert.challenge.ui.screen.Screen
import com.robert.challenge.ui.screen.details.DetailScreen
import com.robert.challenge.ui.screen.home.HomeScreen
import com.robert.challenge.viewmodel.FlickrViewModel
import okhttp3.Challenge


@Composable
fun ChallengeApp() {
    val navController = rememberNavController()
    ChallengeAppNavHost(navController = navController)

}


@Composable
fun ChallengeAppNavHost(
    navController: NavHostController
) {

    val viewModel: FlickrViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                navController = navController,
                flickrViewModel = viewModel
            )
        }
        composable(route = Screen.Detail.route) {
            DetailScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

