package com.whc.asmrMusic.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
class Diary : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var text = ""
    var updateCount = 0
    var createTime = Date()
    var updateTime = Date()
    override fun toString(): String {
        return "Diary(id=$id, text='$text', updateCount=$updateCount, createTime=$createTime, updateTime=$updateTime)"
    }

}