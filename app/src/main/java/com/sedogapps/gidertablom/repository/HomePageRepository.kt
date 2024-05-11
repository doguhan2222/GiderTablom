package com.sedogapps.gidertablom.repository

import com.sedogapps.gidertablom.model.CategoryListview
import com.sedogapps.gidertablom.room.dao.CategoriesDao
import com.sedogapps.gidertablom.room.dao.ExpensesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomePageRepository @Inject constructor(private val expensesDao: ExpensesDao ,private val categoriesDao: CategoriesDao) {


    suspend fun getAllExpenses(): Flow<List<CategoryListview>> {
        return flow {
            val categoryListViewList: MutableList<CategoryListview> = mutableListOf()

            categoriesDao.getAllCategories().collect { categoryList ->
                categoryList.forEach { category ->
                    val expensesForCategory = expensesDao.getExpensesForCategory(category.id)
                    val categoryListview = CategoryListview(
                        categoryName = category.name,
                        categoryExpenses = expensesForCategory
                    )
                    categoryListViewList.add(categoryListview)
                }
                emit(categoryListViewList)
            }
        }
    }





//        categoriesDao.getAllCategories().collect { categoriesList ->
//            // Her yeni veri geldiğinde bu blok çalışacak
//            // categoriesList içindeki verileri kullanabilirsiniz
//            categoriesList.forEach { category ->
//                // Kategori adını kullanabilirsiniz
//                val categoryName = category.name
//                val expensesResult = expensesDao.getAllExpenses()
//
//                expensesResult.firstOrNull()?.let { firstExpense ->
//                    val result = expensesDao.getExpenseWithCategory(firstExpense.get(0).category_id)
//                    println("id " + result?.expenses?.description + " category " + result?.category?.name)
//
//                    // result2'yi kullanarak devam edin
//                }
//            }
//
//        }






    fun getTodayExpenses(){

    }

    fun getThisWeekExpenses(){

    }

    fun getThisMonthExpenses(){

    }


}