package com.appexpensetrackers

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.appexpensetrackers.ui.theme.ExpenseTrackersTheme
import com.appexpensetrackers.ui.theme.zinc
import com.codewithfk.expensetracker.android.NavHostScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseTrackersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
//                    HomeScreen()
//                     AddExpense() // Uncomment when ready to implement this screen
                    NavHostScreen()
                    window.statusBarColor = zinc.toArgb() // Change status bar color
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column {
        // Add content here if needed
    }
}

@Preview(showBackground = true)
@Composable
fun ShowScreen() {
    HomeScreen(rememberNavController())
//     AddExpense(rememberNavController()) // Uncomment when ready to implement this screen
}
