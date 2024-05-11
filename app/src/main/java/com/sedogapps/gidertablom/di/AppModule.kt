package com.sedogapps.gidertablom.di


import android.app.Application
import android.content.Context
import com.sedogapps.gidertablom.room.AppDatabase
import com.sedogapps.gidertablom.room.dao.CategoriesDao
import com.sedogapps.gidertablom.room.dao.ExpensesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Singleton
    @Provides
    fun provideNoteDatabase(application: Application): AppDatabase {
        return AppDatabase.getDatabase(application.applicationContext);
//        return Room.databaseBuilder(
//            application,
//            AppDatabase::class.java,
//            "app_database"
//        ).build()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(database: AppDatabase): CategoriesDao {

        return database.categorisDao()
    }

    @Singleton
    @Provides
    fun provideExpenseDao(database: AppDatabase): ExpensesDao {
        return database.expensesDao()
    }
}
