package com.appexpensetrackers.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.appexpensetrackers.data.model.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseEntityDao {

    @Query("SELECT * FROM expanse_table")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>
    
    @Insert
    suspend fun insertExpense(expense: ExpenseEntity)
    
    @Delete
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)

    @Update
    suspend fun updateExpense(expenseEntity: ExpenseEntity)

    @Query("SELECT * FROM expanse_table WHERE id = :expenseId LIMIT 1")
    fun getExpenseById(expenseId: Int): Flow<ExpenseEntity>
}
