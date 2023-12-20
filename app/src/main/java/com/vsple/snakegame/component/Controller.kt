package com.vsple.snakegame.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vsple.snakegame.game.SnakeDirection
import com.vsple.snakegame.ui.theme.*
/*
This composable hold the controlling of snake in our game button
or
This will help us to direct the snake by knowing its current direction
By default we assign snake direction to Right
 */
@Composable
fun Controller(onDirectionChange: (Int) -> Unit) {
    val buttonSize = Modifier.size(size64dp)
    val currentDirection = remember { mutableStateOf(SnakeDirection.Right) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(padding24dp)
    ) {
        GameControllerButton(icon = Icons.Default.KeyboardArrowUp) {
            if (currentDirection.value != SnakeDirection.Down) {
                onDirectionChange.invoke(SnakeDirection.Up)
                currentDirection.value = SnakeDirection.Up
            }
        }
        Row {
            GameControllerButton(icon = Icons.Default.KeyboardArrowLeft) {
                if (currentDirection.value != SnakeDirection.Right) {
                    onDirectionChange.invoke(SnakeDirection.Left)
                    currentDirection.value = SnakeDirection.Left
                }
            }
            Spacer(modifier = buttonSize)
            GameControllerButton(icon = Icons.Default.KeyboardArrowRight) {
                if (currentDirection.value != SnakeDirection.Left) {
                    onDirectionChange.invoke(SnakeDirection.Right)
                    currentDirection.value = SnakeDirection.Right
                }
            }
        }
        GameControllerButton(icon = Icons.Default.KeyboardArrowDown) {
            if (currentDirection.value != SnakeDirection.Up) {
                onDirectionChange.invoke(SnakeDirection.Down)
                currentDirection.value = SnakeDirection.Down
            }
        }
    }
}