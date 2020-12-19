package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.AddressVO

class AddressVOListTypeConverter {
    @TypeConverter
    fun toString(addressVOList:List<AddressVO>?):String{
        return Gson().toJson(addressVOList)
    }
    @TypeConverter
    fun toAddressVOList(addressVOListJsonString:String):List<AddressVO> {
        val addressVOListType = object : TypeToken<ArrayList<AddressVO>>(){}.type
        return Gson().fromJson(addressVOListJsonString,addressVOListType)?: arrayListOf()
    }
}