package com.vsple.snakegame.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vsple.snakegame.ui.screen.HighScoreScreen
import com.vsple.snakegame.ui.screen.MenuScreen
import com.vsple.snakegame.ui.screen.SettingScreen

@Composable
fun HomeScreenNavigation(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = HomeGraph.Menu.route) {
        composable(route = HomeGraph.Menu.route) { MenuScreen(navHostController) }
        composable(route = HomeGraph.HighScores.route) { HighScoreScreen(navHostController) }
        composable(route = HomeGraph.Settings.route) { SettingScreen(navHostController) }
    }
}
