package com.vsple.snakegame.navigation

sealed class HomeGraph(val route:String) {
    object Menu : HomeGraph(route = "menu_screen")
    object HighScores : HomeGraph(route = "high_scores_screen")
    object Settings : HomeGraph(route = "settings_screen")
}