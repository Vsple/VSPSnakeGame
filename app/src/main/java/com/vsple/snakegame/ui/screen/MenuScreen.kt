package com.vsple.snakegame.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.vsple.snakegame.R
import com.vsple.snakegame.activity.GameActivity
import com.vsple.snakegame.common.launchActivity
import com.vsple.snakegame.component.DisplayLarge
import com.vsple.snakegame.component.MenuButton
import com.vsple.snakegame.navigation.HomeGraph
import com.vsple.snakegame.ui.theme.border2dp
import com.vsple.snakegame.ui.theme.padding16dp
import com.vsple.snakegame.ui.theme.padding64dp
import com.vsple.snakegame.ui.theme.width248dp

@Composable
fun MenuScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding16dp)
            .border(width = border2dp, color = MaterialTheme.colorScheme.onBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val context = LocalContext.current
        DisplayLarge(text = stringResource(id = R.string.app_name))
        MenuButton(
            modifier = Modifier
                .width(width248dp)
                .padding(top = padding64dp),
            text = stringResource(R.string.new_game)
        ) {
            context.launchActivity<GameActivity>()
        }
        MenuButton(
            modifier = Modifier.width(width248dp),
            text = stringResource(id = R.string.high_score)
        ) {
            navHostController.navigate(HomeGraph.HighScores.route)
        }
        MenuButton(
            modifier = Modifier.width(width248dp),
            text = stringResource(R.string.settings)
        ) {
            navHostController.navigate(HomeGraph.Settings.route)
        }
    }
}