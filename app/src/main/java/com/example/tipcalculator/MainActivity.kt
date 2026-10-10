package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UI_Work(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UI_Work(modifier: Modifier = Modifier) {
    val input_sum = remember{mutableStateOf("")}
    val input_eat_count = remember{mutableStateOf("")}
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    val sale_percent = listOf("3%", "5%", "7%", "10%")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(sale_percent[0]) }
    val res = remember{mutableStateOf("")}

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    )
    {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Сумма заказа", modifier.padding(8.dp))
            TextField(
                value = input_sum.value,
                onValueChange = { newText -> input_sum.value = newText },
                modifier = Modifier.width(200.dp)
            )
        }
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Количество блюд", modifier.padding(8.dp))
            TextField(
                value = input_eat_count.value,
                onValueChange = { newText -> input_eat_count.value = newText },
                modifier = Modifier.width(80.dp)
            )
        }
        Text("Чаевые", modifier = Modifier
            .padding(10.dp)
            .align(Alignment.Start))
        Slider(
            modifier = Modifier.padding(10.dp),
            valueRange = 0f..25f,
            value = sliderPosition,
            onValueChange = { sliderPosition = it }
        )
        Text(
            text = sliderPosition.toInt().toString()
        )
        Row(Modifier.selectableGroup(), verticalAlignment = Alignment.CenterVertically)
        {
            Text("Скидка:", fontSize = 25.sp)
            sale_percent.forEach { text ->
                Column(horizontalAlignment = Alignment.CenterHorizontally)
                {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) }
                    )
                    Text( text = text, fontSize = 25.sp )
                }
            }
        }
        Text("Итоговая сумма: " + res.value, fontSize = 20.sp, modifier = Modifier.padding(15.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TipsPreview() {
    TipCalculatorTheme {
        UI_Work()
    }
}