package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.MessageVO

class MessageVOListTypeConverter {
    @TypeConverter
    fun toString(messageVOList:List<MessageVO>):String {
        return Gson().toJson(messageVOList)
    }
    @TypeConverter
    fun toMessageVOList(messageVOListJsonString:String):List<MessageVO> {
        val messageVOListType = object : TypeToken<ArrayList<MessageVO>>(){}.type
        return Gson().fromJson(messageVOListJsonString,messageVOListType)
    }
}