package com.appexpensetrackers

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
import java.util.Calendar
import java.util.Locale

@Composable
fun AddExpense(navController: NavController) {
    val context = LocalContext.current
    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(context)
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.image1),
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxWidth()
            )
            // Add Expense Form
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back_button),
                        contentDescription = "Back Button",
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    )
                    Text(
                        text = "Add Expense",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "Menu Icon"
                    )
                }

                Spacer(modifier = Modifier.height(0.dp))

                // Form Card with NavController passed down
                FormCard(viewModel = homeViewModel, navController = navController)
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun FormCard(viewModel: HomeViewModel, navController: NavController) {
    val context = LocalContext.current
    var expenseName by remember { mutableStateOf("") }
    var expenseCategory by remember { mutableStateOf("") }
    var expenseAmount by remember { mutableStateOf("") }
    var expenseDate by remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            calendar.set(year, month, dayOfMonth)
            expenseDate = dateFormat.format(calendar.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = 40.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Category Field
            LabeledTextField(
                label = "Category",
                value = expenseCategory,
                onValueChange = { expenseCategory = it },
                placeholder = "Category"
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Name Field
            LabeledTextField(
                label = "Name",
                value = expenseName,
                onValueChange = { expenseName = it },
                placeholder = "Name"
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Amount Field
            LabeledTextField(
                label = "Amount",
                value = expenseAmount,
                onValueChange = { expenseAmount = it },
                placeholder = "Amount"
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Date Picker Field
            OutlinedTextField(
                value = expenseDate,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Choose Date", fontSize = 13.sp, color = Color.Black) },
                placeholder = {
                    Text(
                        text = "Choose Date",
                        color = Color.Gray
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { datePickerDialog.show() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.calender),
                            contentDescription = "Select Date"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Submit Button
            Button(
                onClick = {
                    val fieldsAreValid = listOf(expenseName, expenseAmount, expenseDate, expenseCategory).all { it.isNotBlank() }
                    if (fieldsAreValid) {
                        try {
                            val amount = expenseAmount.toDouble()
                            viewModel.addExpense(
                                ExpenseEntity(
                                    name = expenseName,
                                    category = expenseCategory,
                                    date = expenseDate,
                                    amount = amount,
                                    type = "Expense",
                                    title = expenseName
                                )
                            )
                            Toast.makeText(context, "Expense Added!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } catch (e: NumberFormatException) {
                            Toast.makeText(context, "Invalid amount format.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Please fill all fields.", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = zinc),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add Expense", color = Color.White)
            }
        }
    }
}

@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            textStyle = TextStyle(color = Color.Black)
        )
    }
}

@Composable
@Preview
fun AddExpensePreview() {
    AddExpense(rememberNavController())
}
