package com.example.a19_2_dataflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a19_2_dataflow.ui.theme.Orange
import com.example.a19_2_dataflow.ui.theme.PurpleGrey40
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMICalculatorApp()
        }
    }
}

@Composable
fun BMICalculatorApp() {

    var weight by remember { mutableFloatStateOf(35f) }
    var height by remember { mutableFloatStateOf(1.50f) }

    val bmi by remember { derivedStateOf { weight / height.pow(2) } }
    val interpretation by remember {
        derivedStateOf {
            when (bmi) {
                in 0f..<16f -> "Выраженный дефицит" to Color.Blue
                in 16f..<18.5f -> "Недостаточная масса" to Color.Cyan
                in 18.5f..<25f -> "Норма" to Color.Green
                in 25f..<30f -> "Избыточная масса" to Color.Yellow
                in 30f..<35f -> "Ожирение 1 степени" to Color.Magenta
                in 35f..<40f -> "Ожирение 2 степени" to Orange
                else -> "Ожирение 3 степени" to Color.Red
            }
        }
    }

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Рост: ${"%.2f".format(height)} м\t\t\t\t\t\t",
                        fontSize = 24.sp,
                        modifier = Modifier
                            .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
                            .padding(16.dp)
                            .clickable { height += 0.05f }
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = "Масса: ${"%.2f".format(weight)} кг\t",
                        fontSize = 24.sp,
                        modifier = Modifier
                            .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
                            .padding(16.dp)
                            .clickable { weight += 5f }
                    )

                    Text(
                        text = "ИМТ: ${"%.2f".format(bmi)}",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(16.dp)
                    )

                    val (interpretationText, interpretationColor) = interpretation
                    Text(
                        text = interpretationText,
                        fontSize = 24.sp,
                        color = interpretationColor,
                        modifier = Modifier
                            .background(PurpleGrey40)
                            .padding(16.dp)
                    )

                    Text(
                        text = "Сброс",
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                weight = 35f
                                height = 1.50f
                            }
                    )
                }
            }
        })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    BMICalculatorApp()
}