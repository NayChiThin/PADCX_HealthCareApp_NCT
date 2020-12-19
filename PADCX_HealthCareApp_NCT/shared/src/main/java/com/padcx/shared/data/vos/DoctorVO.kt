package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "doctors")
data class DoctorVO(
    @PrimaryKey
    @SerializedName("id") var id:String = "",
    @SerializedName("name") var name:String? = "",
    @SerializedName("phonenumber") var phonenumber:String? = "",
    @SerializedName("speciality") var speciality:String? = "",
    @SerializedName("profilephoto") var profilephoto:String? = "",
    @SerializedName("description") var description:String? = "",
    @SerializedName("certificate") var certificate:String? = "",
    @SerializedName("address") var address:String? = "",
    @SerializedName("experience") var experience:String? = "",
    @SerializedName("gender") var gender:String? = "",
    @SerializedName("dob") var dob:String? = ""
):Serializable
fun DoctorVO.toDoctorMap():HashMap<String,String?> {
    val doctorMap = hashMapOf(
        "id" to id,
        "name" to name,
        "phonenumber" to phonenumber,
        "speciality" to speciality,
        "profilephoto" to profilephoto,
        "description" to description,
        "certificate" to certificate,
        "address" to address,
        "experience" to experience,
        "gender" to gender,
        "dob" to dob
    )
    return doctorMap
}