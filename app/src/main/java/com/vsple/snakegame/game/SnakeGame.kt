package com.vsple.snakegame.game

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun SnakeGame() {
    var snake by remember { mutableStateOf(listOf(Offset(0f, 0f))) }
    var direction by remember { mutableStateOf(Direction.RIGHT) }
    var isRunning by remember { mutableStateOf(true) }
    var food by remember { mutableStateOf(generateFoodPosition()) }

    var snakeSize by remember { mutableStateOf(1) }

    LaunchedEffect(Unit) {
        while (isRunning) {
            delay(200) // Adjust snake speed by changing delay
            snake = moveSnake(snake, direction)

            if (isFoodEaten(snake, food)) {
                snakeSize++
                food = generateFoodPosition()
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SnakeCanvas(
            snake = snake,
            food = food,
            onDirectionChange = { newDirection ->
                if (isOppositeDirection(direction, newDirection).not()) {
                    direction = newDirection
                }
            }
        )
    }
}

@Composable
fun SnakeCanvas(
    snake: List<Offset>,
    food: Offset,
    onDirectionChange: (Direction) -> Unit
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val head = snake.first()
                    val tapX = offset.x
                    val tapY = offset.y

                    val snakeCenterX = head.x + 25 // Assuming snake segment size is 50x50
                    val snakeCenterY = head.y + 25 // Adjust accordingly based on actual size

                    val deltaX = tapX - snakeCenterX
                    val deltaY = tapY - snakeCenterY

                    if (kotlin.math.abs(deltaX) > kotlin.math.abs(deltaY)) {
                        // Horizontal swipe
                        onDirectionChange(if (deltaX > 0) Direction.RIGHT else Direction.LEFT)
                    } else {
                        // Vertical swipe
                        onDirectionChange(if (deltaY > 0) Direction.DOWN else Direction.UP)
                    }
                }
            }
    ) {
        drawFood(food)
        drawSnake(snake)
    }
}

fun generateFoodPosition(): Offset {
    val randomX = Random.nextInt(0, 10) * 50f // Assuming the grid size is 50x50
    val randomY = Random.nextInt(0, 10) * 50f // Adjust according to your grid size
    return Offset(randomX, randomY)
}

fun isFoodEaten(snake: List<Offset>, food: Offset): Boolean {
    val head = snake.first()
    return head == food
}

fun isOppositeDirection(currDirection: Direction, newDirection: Direction): Boolean {
    return when (currDirection) {
        Direction.UP -> newDirection == Direction.DOWN
        Direction.DOWN -> newDirection == Direction.UP
        Direction.LEFT -> newDirection == Direction.RIGHT
        Direction.RIGHT -> newDirection == Direction.LEFT
    }
}


enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

fun moveSnake(snake: List<Offset>, direction: Direction): List<Offset> {
    val head = snake[0]
    val newHead = when (direction) {
        Direction.UP -> Offset(head.x, head.y - 50)
        Direction.DOWN -> Offset(head.x, head.y + 50)
        Direction.LEFT -> Offset(head.x - 50, head.y)
        Direction.RIGHT -> Offset(head.x + 50, head.y)
    }
    val newSnake = mutableListOf(newHead)
    newSnake.addAll(snake.dropLast(1))
    return newSnake
}

fun DrawScope.drawSnake(snake: List<Offset>) {
    snake.forEach { segment ->
        drawRect(
            color = Color.Green,
            topLeft = segment,
            size = Size(50f, 50f)
        )
    }
}

fun DrawScope.drawFood(food: Offset) {
    val foodSize = 50f // Size of the food
    val foodColor = Color.Red // Color of the food

    drawRect(
        color = foodColor,
        topLeft = food,
        size = Size(foodSize, foodSize)
    )
}
