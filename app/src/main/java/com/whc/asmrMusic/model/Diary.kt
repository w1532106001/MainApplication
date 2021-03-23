package com.whc.asmrMusic.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Diary {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var text = ""
    var updateCount = 0
    var createTime = 0L
    var updateTime = 0L
    override fun toString(): String {
        return "Diary(id=$id, text='$text', updateCount=$updateCount, createTime=$createTime, updateTime=$updateTime)"
    }

}