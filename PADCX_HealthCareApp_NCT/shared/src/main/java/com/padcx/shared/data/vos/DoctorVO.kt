package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "doctors")
data class DoctorVO(
    @PrimaryKey
    @SerializedName("id") var id:String? = null,
    @SerializedName("name") var name:String? = null,
    @SerializedName("phonenumber") var phonenumber:String? = null,
    @SerializedName("speciality") var speciality:String? = null,
    @SerializedName("profilephoto") var profilephoto:String? = null,
    @SerializedName("description") var description:String? = null,
    @SerializedName("certificate") var certificate:String? = null
)
fun DoctorVO.toDoctorMap():HashMap<String,String?> {
    val doctorMap = hashMapOf(
        "id" to id,
        "name" to name,
        "phonenumber" to phonenumber,
        "speciality" to speciality,
        "profilephoto" to profilephoto,
        "description" to description,
        "certificate" to certificate
    )
    return doctorMap
}