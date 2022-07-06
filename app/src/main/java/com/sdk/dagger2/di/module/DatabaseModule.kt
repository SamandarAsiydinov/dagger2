package com.sdk.dagger2.di.module

import android.content.Context
import androidx.room.Room
import com.sdk.dagger2.database.UserDatabase
import com.sdk.dagger2.database.dao.UserDao
import com.sdk.dagger2.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideUserDatabase(): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            Constants.DB_NAME
        ).build()
    }
    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao = userDatabase.userDao()
}