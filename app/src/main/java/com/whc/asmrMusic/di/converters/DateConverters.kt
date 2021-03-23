package com.whc.asmrMusic.di.converters

import androidx.room.TypeConverter
import java.sql.Date

object DateConverters {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return when (timestamp) {
            null -> null
            else -> Date(timestamp)
        }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}