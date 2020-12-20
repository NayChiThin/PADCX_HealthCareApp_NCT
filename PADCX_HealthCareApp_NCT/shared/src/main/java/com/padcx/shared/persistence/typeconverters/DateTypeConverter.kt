package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateTypeConverter {
    private val timeZone : TimeZone = TimeZone.getTimeZone("IST")
    private val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @TypeConverter
    fun toString(date:Date):String?{
        df.timeZone = timeZone
        return df.format(date)?:""
    }
    @TypeConverter
    fun toDate(date:String):Date? {
        df.timeZone = timeZone
        return df.parse(date)?:Date()
    }
}