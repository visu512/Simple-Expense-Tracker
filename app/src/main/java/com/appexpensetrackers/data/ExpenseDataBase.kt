package com.appexpensetrackers.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appexpensetrackers.data.model.ExpenseEntity  // Ensure this line is added at the top
import com.appexpensetrackers.data.dao.ExpenseEntityDao

@Database(entities = [ExpenseEntity::class], version = 1)  // Correct usage of entity list
abstract class ExpenseDataBase : RoomDatabase() {

    abstract fun expenseEntityDao(): ExpenseEntityDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseDataBase? = null

        fun getDatabase(context: Context): ExpenseDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDataBase::class.java,
                    "expense_database"
                )
                    .fallbackToDestructiveMigration() // You don't need "from(1, 2)" unless it's required.
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
