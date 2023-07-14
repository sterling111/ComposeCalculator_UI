package com.example.composecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecalculator.ButtonColor.MAIN_COLOR
import com.example.composecalculator.ButtonColor.VARIANT1_COLOR
import com.example.composecalculator.ui.theme.ComposeCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator()
                }
            }
        }
    }
}

@Composable
fun Calculator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        InputDisplay()
        CalculatorGrid()
    }
}

@Composable
fun InputDisplay() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        text = "0",
        textAlign = TextAlign.End,
        fontSize = 56.sp
    )

}

@Composable
fun CalculatorGrid() {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        rows.map{ buttonSpecs ->
            CalculatorRow(buttonSpecs)
        }
    }
}

@Composable
fun CalculatorRow(values: List<ButtonSpec>) {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        values.map{ buttonSpec ->
            CalculatorButton(buttonSpec)
        }
    }
}

@Composable
fun CalculatorButton(buttonSpec: ButtonSpec) {
    OutlinedButton(
        onClick = { /*TODO*/ },
        shape = CircleShape,
        border = BorderStroke(1.dp, Color.Transparent),
        contentPadding = PaddingValues(18.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            buttonSpec.getBackgroundColor()
        )
    ) {
        Text(
            text = buttonSpec.label,
            color = buttonSpec.getTextColor(),
            fontSize = 22.sp
        )
    }
}
private fun ButtonSpec.getBackgroundColor(): Color {
    return when(color) {
        MAIN_COLOR,
        VARIANT1_COLOR -> {
            Color.hsl(0.88f, 0.4f, 0.9f)
        }
        ButtonColor.VARIANT2_COLOR -> Color(0xff18678d)
    }
}
private fun ButtonSpec.getTextColor():Color {
    return when(color) {
        MAIN_COLOR -> {
            Color.hsl(0.99f, 0.29f, 0.47f)
        }
        VARIANT1_COLOR -> Color(0xff18678d)
        ButtonColor.VARIANT2_COLOR -> Color.White
    }
}

val rows = listOf(
    listOf(
        ButtonSpec(label = "AC", color = MAIN_COLOR),
        ButtonSpec(label = "C", color = VARIANT1_COLOR),
        ButtonSpec(label = "%", color = VARIANT1_COLOR),
        ButtonSpec(label = "/", color = VARIANT1_COLOR),
    ),
    listOf(
        ButtonSpec(label = "7", color = MAIN_COLOR),
        ButtonSpec(label = "8", color = MAIN_COLOR),
        ButtonSpec(label = "9", color = MAIN_COLOR),
        ButtonSpec(label = "×", color = VARIANT1_COLOR),
    ),
    listOf(
        ButtonSpec(label = "4", color = MAIN_COLOR),
        ButtonSpec(label = "5", color = MAIN_COLOR),
        ButtonSpec(label = "6", color = MAIN_COLOR),
        ButtonSpec(label = "-", color = VARIANT1_COLOR),
    ),
    listOf(
        ButtonSpec(label = "1", color = MAIN_COLOR),
        ButtonSpec(label = "2", color = MAIN_COLOR),
        ButtonSpec(label = "3", color = MAIN_COLOR),
        ButtonSpec(label = "+", color = VARIANT1_COLOR),
    ),
    listOf(
        ButtonSpec(label = "0", color = MAIN_COLOR),
        ButtonSpec(label = ".", color = MAIN_COLOR),
        ButtonSpec(label = "=", color = VARIANT1_COLOR),
        ButtonSpec(label = "M+", color = VARIANT1_COLOR),
    ),
    listOf(
        ButtonSpec(label = "MC", color = MAIN_COLOR),
        ButtonSpec(label = "MR", color = MAIN_COLOR),
    ),
)

data class ButtonSpec(
    val label: String,
    val color: ButtonColor
)
enum class ButtonColor {
    MAIN_COLOR,
    VARIANT1_COLOR,
    VARIANT2_COLOR
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeCalculatorTheme {
        Calculator()
    }
}

