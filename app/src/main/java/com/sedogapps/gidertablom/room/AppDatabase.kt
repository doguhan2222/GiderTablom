package com.sedogapps.gidertablom.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sedogapps.gidertablom.room.dao.CategoriesDao
import com.sedogapps.gidertablom.room.dao.ExpensesDao
import com.sedogapps.gidertablom.room.entities.Categories
import com.sedogapps.gidertablom.room.entities.Expenses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Expenses::class, Categories::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expensesDao(): ExpensesDao
    abstract fun categorisDao(): CategoriesDao

    companion object {
        // Veritabanını tek seferde oluşturmak için kullanılacak tekil örnek
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Başlangıç verilerini eklemek için çağrılır
                            INSTANCE?.let { database ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    if (database.categorisDao().getCategoriesCount() == 0) {
                                        // Eğer kategoriler daha önce eklenmemişse ekle
                                        populateDatabase(database.categorisDao())
                                    }
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private suspend fun populateDatabase(categoriesDao: CategoriesDao) {
            // Başlangıç verilerini ekleyin
            categoriesDao.insert(Categories(name = "Kategori 1"))
            categoriesDao.insert(Categories(name = "Kategori 2"))
            categoriesDao.insert(Categories(name = "Kategori 3"))
            categoriesDao.insert(Categories(name = "Kategori 4"))
            categoriesDao.insert(Categories(name = "Kategori 5"))
        }
    }
}