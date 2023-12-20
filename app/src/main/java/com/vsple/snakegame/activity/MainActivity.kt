package com.vsple.snakegame.activity

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.vsple.snakegame.common.BaseComponentActivity
import com.vsple.snakegame.navigation.HomeScreenNavigation

class MainActivity : BaseComponentActivity() {
    @Composable
    override fun Content() {
        val navHostController = rememberNavController()
        HomeScreenNavigation(navHostController = navHostController)
    }

}
