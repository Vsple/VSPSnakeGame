package com.vsple.snakegame.ui.screen

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.vsple.snakegame.R
import com.vsple.snakegame.common.DataPreference
import com.vsple.snakegame.component.AppBar
import com.vsple.snakegame.component.DisplayMedium
import com.vsple.snakegame.component.MenuButton
import com.vsple.snakegame.ui.theme.border2dp
import com.vsple.snakegame.ui.theme.padding16dp
import com.vsple.snakegame.ui.theme.padding64dp
import com.vsple.snakegame.ui.theme.width248dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navHostController: NavHostController) {
    val dataStore = DataPreference(LocalContext.current)
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current
    AppBar(
        title = stringResource(R.string.title_settings),
        onBackClicked = { navHostController.popBackStack() }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = padding16dp,
                    start = padding16dp,
                    end = padding16dp
                )
                .border(width = border2dp, color = MaterialTheme.colorScheme.onBackground),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DisplayMedium(
                modifier = Modifier.padding(
                    top = padding64dp,
                    bottom = padding16dp,
                    start = padding16dp,
                    end = padding16dp
                ),
                text = stringResource(id = R.string.player_name),
                textAlign = TextAlign.Center
            )
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
            TextField(
                value = text,
                onValueChange = { text = it },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    focusedTextColor = MaterialTheme.colorScheme.onBackground,
                    cursorColor = MaterialTheme.colorScheme.onBackground,
                ),
                singleLine = true,
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth()
                    .padding(horizontal = padding64dp)
                    .border(width = border2dp, color = MaterialTheme.colorScheme.onBackground)
            )
            MenuButton(
                text = stringResource(R.string.save), modifier = Modifier
                    .width(width248dp)
                    .padding(padding16dp)
            ) {
                if (text.text.isEmpty() || text.text.isBlank()) {
                    Toast.makeText(context, R.string.please_enter_player_name, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    scope.launch {
                        dataStore.savePlayerName(text.text.trim())
                        Toast.makeText(context, R.string.player_name_updated, Toast.LENGTH_SHORT)
                            .show()
                        navHostController.popBackStack()
                    }
                }

            }
        }
    }
}