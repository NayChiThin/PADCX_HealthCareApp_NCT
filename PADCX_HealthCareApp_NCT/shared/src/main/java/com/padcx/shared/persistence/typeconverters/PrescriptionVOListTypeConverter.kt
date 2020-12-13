package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.PrescriptionVO

class PrescriptionVOListTypeConverter {
    @TypeConverter
    fun toString(prescriptionVOList:List<PrescriptionVO>) : String {
        return Gson().toJson(prescriptionVOList)
    }
    @TypeConverter
    fun toPrescriptionVOList(prescriptionVOListJsonString:String):List<PrescriptionVO> {
        val prescriptionVOListType = object : TypeToken<ArrayList<PrescriptionVO>>(){}.type
        return Gson().fromJson(prescriptionVOListJsonString,prescriptionVOListType)
    }
}