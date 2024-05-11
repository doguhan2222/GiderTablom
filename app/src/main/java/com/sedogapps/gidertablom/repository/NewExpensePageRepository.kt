package com.sedogapps.gidertablom.repository

import android.util.Log
import com.sedogapps.gidertablom.room.dao.CategoriesDao
import com.sedogapps.gidertablom.room.dao.ExpensesDao
import com.sedogapps.gidertablom.room.entities.Categories
import com.sedogapps.gidertablom.room.entities.Expenses
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class NewExpensePageRepository @Inject constructor(private val expensesDao: ExpensesDao, private val categoriesDao: CategoriesDao){

    suspend fun insertNewExpense(newExpenseModel: Expenses): Boolean {
        return try {
            expensesDao.insert(newExpenseModel)
            true
        } catch (e: Exception) {
            Log.d("NewExpensePageRepository", e.message.toString())
            false
        }
    }


     fun getAllCategories(): Flow<List<Categories>> {
        return try {
            categoriesDao.getAllCategories()
        } catch (e: Exception) {
            Log.e("NewExpensePageRepository", "Error fetching categories", e)
            return flowOf(emptyList())
        }
    }

}
