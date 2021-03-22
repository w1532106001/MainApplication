package com.whc.asmrMusic.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Diary {
    @PrimaryKey
    var id = 0
    var text = ""
    var updateCount = 0
    var createTime = Date()
    var updateTime = Date()
}