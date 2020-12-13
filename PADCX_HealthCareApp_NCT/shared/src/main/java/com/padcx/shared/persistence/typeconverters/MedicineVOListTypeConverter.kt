package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.MedicineVO

class MedicineVOListTypeConverter {
    @TypeConverter
    fun toString(medicineVOList:ArrayList<MedicineVO>):String {
        return Gson().toJson(medicineVOList)
    }
    @TypeConverter
    fun toMedicineVOList(medicineVOListJsonString:String):ArrayList<MedicineVO>{
        val medicineVOListType = object : TypeToken<ArrayList<MedicineVO>>(){}.type
        return Gson().fromJson(medicineVOListJsonString,medicineVOListType)
    }
}