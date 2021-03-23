package com.whc.asmrMusic.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.whc.asmrMusic.model.Diary

@Dao
interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(diary: Diary): Long

    @Query("SELECT * FROM diary where text like :searchText")
    fun getAllDiary(searchText:String): LiveData<MutableList<Diary>>

//    @Query("SELECT * FROM diary WHERE userId = :userId and unitCode =:unitCode")
//    fun getAllDiary(userId: String, unitCode: String): List<Diary>?
}