package devart.website.healthpointscalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var currentScreen by remember { mutableIntStateOf(0) }
    var maxHP by remember { mutableStateOf("") }

    Column(modifier) {
        when(currentScreen) {
            1 -> HealthBarScreen(
                maxHP = maxHP.toIntOrNull() ?: 0
            )
            2 -> EnterValueScreen(
                stateHealth = maxHP,
                onValueChange = { newText ->
                    if (newText.matches(Regex("[1-9][0-9]*")) && newText != " ") {
                        maxHP = newText
                    }
                },
                currentScreen = {
                    currentScreen = 1
                }
            )
            else -> ChoiceScreen(
                enterValue = {
                    currentScreen = 2
                },
                randomHealth = {
                    currentScreen = 1
                    maxHP = randomNumberHealth().toString()
                }
            )
        }
    }
}

@Composable
fun EnterValueScreen(
    stateHealth: String,
    onValueChange: (String) -> Unit,
    currentScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text("Enter value health")
        TextField(
            value = stateHealth,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        Button(
            onClick = currentScreen
        ) { Text("Next") }
    }
}

@Composable
fun ChoiceScreen(
    enterValue: () -> Unit,
    randomHealth: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = enterValue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60.dp, end = 60.dp, bottom = 16.dp)
        ) {
            Text("Enter Value")
        }
        Button(
            onClick = randomHealth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60.dp, end = 60.dp)
        ) {
            Text("Random Health")
        }
    }
    
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}