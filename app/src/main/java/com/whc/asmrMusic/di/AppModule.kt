package com.whc.asmrMusic.di

import android.app.Application
import androidx.room.Room
import com.whc.asmrMusic.AppConstants
import com.whc.asmrMusic.common.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, AppConstants.DB_NAME)
            .fallbackToDestructiveMigrationFrom(1)
            .build()
    }
}