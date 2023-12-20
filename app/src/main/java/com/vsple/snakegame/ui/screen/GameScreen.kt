package com.vsple.snakegame.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.vsple.snakegame.R
import com.vsple.snakegame.activity.GameActivity
import com.vsple.snakegame.component.AppBar
import com.vsple.snakegame.component.Board
import com.vsple.snakegame.component.Controller
import com.vsple.snakegame.game.GameModule
import com.vsple.snakegame.game.SnakeDirection

@Composable
fun GameScreen(gameModule: GameModule, score: Int) {
    val state = gameModule.state.collectAsState(initial = null)
    val activity = LocalContext.current as GameActivity
    AppBar(
        title = stringResource(id = R.string.your_score, score),
        onBackClicked = { activity.finish() }) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            state.value?.let { Board(it) }
            Controller {
                when (it) {
                    SnakeDirection.Up -> gameModule.move = Pair(0, -1)
                    SnakeDirection.Left -> gameModule.move = Pair(-1, 0)
                    SnakeDirection.Right -> gameModule.move = Pair(1, 0)
                    SnakeDirection.Down -> gameModule.move = Pair(0, 1)
                }
            }
        }
    }
}