package com.sedogapps.gidertablom.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sedogapps.gidertablom.room.entities.ExpenseWithCategory
import com.sedogapps.gidertablom.room.entities.Expenses
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpensesDao {

    @Query("SELECT * FROM Expenses")
     fun getAllExpenses(): Flow<List<Expenses>>

    @Insert
    suspend fun insert(expenses: Expenses)

    @Query("DELETE FROM Expenses WHERE id = :expensesId")
    suspend fun deleteById(expensesId: Int)

    @Query("SELECT * FROM expenses WHERE id = :expensesId")
    suspend fun getExpenseWithCategory(expensesId: Int): ExpenseWithCategory?

    @Query("SELECT * FROM expenses WHERE category_id = :categoryId")
    suspend fun getExpensesForCategory(categoryId: Int): List<Expenses>

}