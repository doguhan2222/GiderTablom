package com.sedogapps.gidertablom.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sedogapps.gidertablom.room.entities.Categories
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoriesDao {

    @Query("SELECT COUNT(*) FROM categories")
    suspend fun getCategoriesCount(): Int

    @Query("SELECT * FROM categories")
     fun getAllCategories(): Flow<List<Categories>>

    @Insert
    suspend fun insert(category: Categories)

    @Query("DELETE FROM categories WHERE id = :categoryId")
    suspend fun deleteById(categoryId: Int)

    @Query("SELECT * FROM categories WHERE id = :categoryId")
    suspend fun getCategoryById(categoryId: Int): Categories?
}