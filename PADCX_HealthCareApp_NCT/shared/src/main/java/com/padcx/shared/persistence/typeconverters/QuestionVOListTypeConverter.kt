package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.QuestionVO

class QuestionVOListTypeConverter {
    @TypeConverter
    fun toString(questionVOList:List<QuestionVO>?):String {
        return Gson().toJson(questionVOList)
    }
    @TypeConverter
    fun toQuestionVOList(questionVOListJsonString:String):List<QuestionVO> {
        val questionVOListType = object : TypeToken<ArrayList<QuestionVO>>(){}.type
        return Gson().fromJson(questionVOListJsonString,questionVOListType)?: arrayListOf()
    }
}