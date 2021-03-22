package com.whc.asmrMusic.common

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.whc.asmrMusic.dao.DiaryDao
import com.whc.asmrMusic.model.Diary

@Database(entities = [Diary::class],version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDiaryDao(): DiaryDao

}