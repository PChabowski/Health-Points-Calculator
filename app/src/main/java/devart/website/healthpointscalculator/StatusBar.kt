package devart.website.healthpointscalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import devart.website.healthpointscalculator.ui.theme.HealthPointsCalculatorTheme

@Composable
fun StatusBar(
    currentValue: Int,
    maxValue: Int,
    sizeCircle: Int,
    valueIsHidden: Boolean,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(sizeCircle.dp)
            .border(4.dp, Color.Black, shape = CircleShape)
            .clip(CircleShape)
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .background(color)
                .height((currentValue * sizeCircle / maxValue).dp)
                .width(sizeCircle.dp)
                .align(Alignment.BottomCenter)
        )
        if (!valueIsHidden) {
            Text(
                text = "$currentValue",
                fontSize = 38.sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun HealthBarStatusScreen() {
    HealthPointsCalculatorTheme {
        StatusBar(
            25,
            50,
            250,
            false,
            Color.Red
        )
    }
}