package com.whc.asmrMusic.common

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.sql.Date


class Converters {
    @TypeConverter
    fun listToString(list: List<String>?): String? = if (list == null || list.isEmpty()) "" else Gson().toJson(list)

    @TypeConverter
    fun stringToList(string: String): List<String>? = if (TextUtils.isEmpty(string)) null else Gson().fromJson<List<String>>(string, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        value?.let { return Date(value) }
        return null
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        date?.let { return date.time }
        return null
    }
}