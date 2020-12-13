package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.DoctorVO

class DoctorVOListTypeConverter {
    @TypeConverter
    fun toString(doctorVOList:List<DoctorVO>):String {
        return Gson().toJson(doctorVOList)
    }
    @TypeConverter
    fun toDoctorVOList(doctorVOListJsonString:String):List<DoctorVO> {
        val doctorVOListType = object : TypeToken<ArrayList<DoctorVO>>(){}.type
        return Gson().fromJson(doctorVOListJsonString,doctorVOListType)
    }
}