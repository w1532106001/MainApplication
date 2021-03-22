package com.whc.asmrMusic.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Diary {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var text = ""
    var updateCount = 0
    var createTime = 0L
    var updateTime = 0L
}