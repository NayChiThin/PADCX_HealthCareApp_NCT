package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcx.shared.persistence.typeconverters.MedicineVOListTypeConverter
import com.padcx.shared.persistence.typeconverters.QuestionVOListTypeConverter

@Entity(tableName = "speciality")
@TypeConverters(QuestionVOListTypeConverter::class,MedicineVOListTypeConverter::class)
data class SpecialityVO(
    @PrimaryKey
    @SerializedName("name") var name:String = "",
    @SerializedName("medicine") var medicine:List<MedicineVO?>? = null,
    @SerializedName("questions")var questions:List<QuestionVO?>? = null,
    @SerializedName("image") var image :String? = null
)
fun SpecialityVO.toSpecialityMap():HashMap<String,String?> {
    val specialityMap = hashMapOf(
        "name" to name,
        "image" to image
    )
    return specialityMap
}