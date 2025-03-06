package devart.website.healthpointscalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import devart.website.healthpointscalculator.ui.theme.HealthPointsCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthPointsCalculatorTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun HealthBar(
    currentHP: Int,
    maxHP: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "$currentHP/$maxHP",
            fontSize = 22.sp,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        StatusBar(
            currentValue = currentHP,
            maxValue = maxHP,
            sizeCircle = 250,
            valueIsHidden = false,
            color = Color.Red,
            modifier = Modifier
                .padding(bottom = 22.dp)
        )
        Text(
            text = "Health Points",
            fontSize = 28.sp
        )
    }
    
}

@Composable
fun ButtonsAddAndSubtractHP(
    onClickAdd: () -> Unit,
    onClickSub: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        IconButton(
            onClick = onClickAdd,
            modifier = Modifier.size(80.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = "Addition Health Points",
                tint = Color(0xff008800),
                modifier = Modifier.size(80.dp)
            )
        }
        Spacer(modifier = Modifier.width(70.dp))
        IconButton(
            onClick = onClickSub,
            modifier = Modifier.size(80.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = "Subtract Health Points",
                tint = Color(0xffcc0000),
                modifier = Modifier.size(80.dp)
            )
        }

    }

}

@Composable
fun HealthBarScreen(maxHP: Int, modifier: Modifier = Modifier) {
    var currentHealthPoint by remember { mutableIntStateOf(maxHP) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        HealthBar(maxHP = maxHP, currentHP = currentHealthPoint)
        Spacer(modifier = Modifier.height(100.dp))
        ButtonsAddAndSubtractHP(
            onClickAdd = {
                if (currentHealthPoint < maxHP) { currentHealthPoint++ }
            },
            onClickSub = {
                if (currentHealthPoint > 0) { currentHealthPoint-- }
            }
        )
    }
    
}

fun randomNumberHealth():Int {
    return (28..155).random()
}

@Preview(showBackground = true)
@Composable
fun HealthBarsStatusPreview() {
    HealthPointsCalculatorTheme {
        MainScreen()
    }
}