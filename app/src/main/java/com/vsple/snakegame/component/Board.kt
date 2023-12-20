package com.vsple.snakegame.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vsple.snakegame.game.GameModule
import com.vsple.snakegame.model.State
import com.vsple.snakegame.ui.theme.*

/*
This composable is use to design the board where we land the snake and it food
*/

@Composable
fun Board(state: State) {
    BoxWithConstraints(Modifier.padding(padding16dp)) {
        val tileSize = maxWidth / GameModule.BOARD_SIZE
        Box(
            Modifier
                .size(maxWidth)
                .border(border2dp, Purple40)
        )
        Box(
            Modifier
                .offset(x = tileSize * state.food.first, y = tileSize * state.food.second)
                .size(tileSize)
                .background(
                    Purple40, CircleShape
                )
        )
        state.snake.forEach {
            Box(
                modifier = Modifier
                    .offset(x = tileSize * it.first, y = tileSize * it.second)
                    .size(tileSize)
                    .background(
                        Purple40, RoundedCornerShape(corner4dp)
                    )
            )
        }
    }
}