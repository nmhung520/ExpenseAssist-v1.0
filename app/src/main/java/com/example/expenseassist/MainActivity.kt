package com.example.expenseassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.expenseassist.ui.theme.ExpenseAssistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseAssistTheme {
                Greeting("Hello World!")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = name)
}