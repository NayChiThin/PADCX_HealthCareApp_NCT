package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "medicine")
data class MedicineVO(
    @PrimaryKey
    @SerializedName("name") var name:String = "",
    @SerializedName("cost") var cost:Long? = null
)
fun MedicineVO.toMedicineMap():HashMap<String,Any?> {
    val medicineMap = hashMapOf(
        "name" to name,
        "cost" to cost
    )
    return medicineMap
}