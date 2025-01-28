package com.appexpensetrackers

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.appexensetrackers.R
import com.appexpensetrackers.data.model.ExpenseEntity
import com.appexpensetrackers.ui.theme.zinc
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(LocalContext.current)
    )

    val expenses by viewModel.expenses.collectAsState(initial = emptyList())
    val balance = viewModel.getBalance(expenses)
    val totalExpenses = viewModel.getTotalExpense(expenses)
    val totalIncome = viewModel.getTotalIncome(expenses)

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.image1),
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                GreetingRow()

                Spacer(modifier = Modifier.height(25.dp))

                CardItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    balance = balance,
                    income = totalIncome,
                    expenses = totalExpenses
                )

                Spacer(modifier = Modifier.height(16.dp))

                TransactionsList(expenses, navController)

                Spacer(modifier = Modifier.height(8.dp))

                CalendarButton(navController)
            }

//            FloatingActionButton(
//                onClick = { navController.navigate("/add") },
//                modifier = Modifier
//                    .align(Alignment.BottomEnd)
//                    .padding(16.dp),
//                containerColor = zinc
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.add),
//                    contentDescription = "Add Expense",
//                    tint = Color.White
//                )
//            }
        }
    }
}

@Composable
fun CalendarButton(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
//        Button(
//            onClick = {
//                // Navigate to the calendar screen (implement navigation logic)
//            },
//            shape = RoundedCornerShape(8.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = zinc),
//            modifier = Modifier.fillMaxWidth(0.8f)
//        ) {
//            Text(
//                text = "View Calendar",
//                color = Color.White,
//                fontWeight = FontWeight.Bold,
//                fontSize = 16.sp
//            )
//        }
    }
}

@Composable
fun GreetingRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Good Morning,",
                fontSize = 13.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "John Doe",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Image(
            painter = painterResource(id = R.drawable.notif),
            contentDescription = "Notification Icon",
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun CardItem(
    balance: String,
    income: String,
    expenses: String,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardDefaults.cardColors(containerColor = zinc)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Total Balance",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = balance,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(45.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CardRowItem("Income", income, R.drawable.income)
                CardRowItem("Expenses", expenses, R.drawable.expense)
            }
        }
    }
}

@Composable
fun CardRowItem(title: String, amount: String, image: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = image),
                contentDescription = title,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = amount,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
    }
}

@Composable
fun TransactionsList(expensesList: List<ExpenseEntity>, navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Transactions History", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "See all", fontSize = 12.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(4.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            expensesList.forEach { transaction ->
                TransactionItem(
                    title = transaction.title,
                    amount = "$${transaction.amount}",
                    amountColor = if (transaction.type == "Income") Color.Green else Color.Red,
                    image = if (transaction.type == "Income") R.drawable.profiles else R.drawable.profiles,
                    category = transaction.category,
                    type = transaction.type,
                    date = formatDate(transaction.date),
                    navController = navController
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TransactionItem(
    title: String,
    amount: String,
    amountColor: Color,
    image: Int,
    category: String,
    type: String,
    date: String,
    navController: NavController,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(45.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Text(text = "$category | $date", fontSize = 12.sp, color = Color.Gray)
            }
        }

        Text(
            text = amount,
            fontSize = 16.sp,
            color = amountColor,
            fontWeight = FontWeight.Bold
        )
    }
}
//
//fun formatDate(timestamp: String): String {
//    return try {
//        val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
//        sdf.format(Date(timestamp.toLong()))
//    } catch (e: Exception) {
//        "Invalid Date"
//    }
//}

fun formatDate(timestamp: String): String {
    return try {
        val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        sdf.format(Date(timestamp.toString()))
    } catch (e: Exception) {
        Log.e("HomeScreen", "Invalid date: $timestamp", e)
        "Invalid Date"
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
