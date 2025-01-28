package com.appexpensetrackers

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.appexpensetrackers.data.ExpenseDataBase
import com.appexpensetrackers.data.dao.ExpenseEntityDao
import com.appexpensetrackers.data.model.ExpenseEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(private val dao: ExpenseEntityDao) : ViewModel() {

    val expenses: Flow<List<ExpenseEntity>> = dao.getAllExpenses()


    fun addExpense(expense: ExpenseEntity) {
        viewModelScope.launch {
            dao.insertExpense(expense)
        }
    }

    fun getBalance(expenses: List<ExpenseEntity>): String {
        val balance = expenses.fold(0.0) { acc, expense ->
            when (expense.type) {
                "Income" -> acc + expense.amount
                "Expense" -> acc - expense.amount
                else -> acc
            }
        }
        return "$${balance.format(2)}"
    }

    fun getTotalIncome(expenses: List<ExpenseEntity>): String {
        val income = expenses
            .filter { it.type.equals("income", true) }
            .sumOf { it.amount }
        return "$${income.format(2)}"
    }

    fun getTotalExpense(expenses: List<ExpenseEntity>): String {
        val expensesTotal = expenses
            .filter { it.type.equals("Expense", true) }
            .sumOf { it.amount }
        return "$${expensesTotal.format(2)}"
    }

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)
}

class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val dao = ExpenseDataBase.getDatabase(context).expenseEntityDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
