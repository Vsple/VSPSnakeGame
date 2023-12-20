package com.vsple.snakegame.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import com.vsple.snakegame.R
import com.vsple.snakegame.common.BaseComponentActivity
import com.vsple.snakegame.common.DataPreference
import com.vsple.snakegame.common.TOP_10
import com.vsple.snakegame.game.GameModule
import com.vsple.snakegame.game.SnakeGame
import com.vsple.snakegame.model.HighScore
import com.vsple.snakegame.ui.screen.EndScreen
import com.vsple.snakegame.ui.screen.GameScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GameActivity : BaseComponentActivity() {
    private lateinit var dataStore: DataPreference
    private val isPlaying = mutableStateOf(true)
    private var score = mutableStateOf(0)
    private lateinit var scope: CoroutineScope
    private lateinit var playerName: String
    private lateinit var highScores: List<HighScore>
    private var gameModule = GameModule(
        scope = lifecycleScope,
        onGameEnded = {
            if (isPlaying.value) {
                isPlaying.value = false
                scope.launch { dataStore.saveHighScore(highScores) }
            }
        },
        onFoodEaten = { score.value++ }
    )

    @Composable
    override fun Content() {
        scope = rememberCoroutineScope()
        dataStore = DataPreference(applicationContext)
        playerName =
            dataStore.getPlayerName.collectAsState(initial = stringResource(id = R.string.default_player_name)).value
        highScores = dataStore.getHighScores.collectAsState(initial = listOf()).value.plus(
            HighScore(playerName, score.value)
        ).sortedByDescending { it.score }.take(TOP_10)
        Column {
            if (isPlaying.value) {
                GameScreen(gameModule, score.value)
               // SnakeGame()
            } else {
                EndScreen(score.value) {
                    score.value = 0
                    gameModule.reset()
                    isPlaying.value = true

                }
            }
        }
    }
}