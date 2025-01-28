package com.appexpensetrackers.data.model  // Correct package declaration

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expanse_table")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null, // auto-generate the ID
    val category: String,
    val name: String,
    val amount: Double,  // Changed from String to Double for amounts
    val date: String,
    val type: String,
    val title: String,
)
