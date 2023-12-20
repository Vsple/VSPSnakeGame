package com.vsple.snakegame.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.vsple.snakegame.R
import com.vsple.snakegame.activity.GameActivity
import com.vsple.snakegame.component.AppBar
import com.vsple.snakegame.component.DisplayLarge
import com.vsple.snakegame.component.MenuButton
import com.vsple.snakegame.component.TitleLarge
import com.vsple.snakegame.ui.theme.padding8dp

@Composable
fun EndScreen(score: Int, onTryAgain: () -> Unit) {
    val activity = LocalContext.current as GameActivity
    AppBar(title = "", onBackClicked = { activity.finish() }) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DisplayLarge(
                modifier = Modifier.padding(padding8dp),
                text = stringResource(R.string.game_over),
                textAlign = TextAlign.Center
            )
            TitleLarge(
                modifier = Modifier.padding(padding8dp),
                text = stringResource(id = R.string.your_score, score),
            )
            MenuButton(text = stringResource(R.string.try_again)) { onTryAgain.invoke() }
        }
    }
}