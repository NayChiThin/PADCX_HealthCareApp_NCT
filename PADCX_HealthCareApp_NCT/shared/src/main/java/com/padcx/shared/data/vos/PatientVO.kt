package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.padcx.shared.persistence.typeconverters.AddressVOListTypeConverter
import com.padcx.shared.persistence.typeconverters.DoctorVOListTypeConverter
import com.padcx.shared.persistence.typeconverters.QuestionVOListTypeConverter
import java.io.Serializable

@Entity(tableName = "patients")
@TypeConverters(AddressVOListTypeConverter::class,DoctorVOListTypeConverter::class,QuestionVOListTypeConverter::class)
data class PatientVO(
    @PrimaryKey
    var id : String = "",
    var name:String? = "",
    var address:List<AddressVO>? = arrayListOf(),
    var profilephoto:String? = "",
    var recentDoctors:List<DoctorVO>? = arrayListOf(),
    var generalQuestions:List<QuestionVO>? = arrayListOf()
):Serializable
fun PatientVO.toPatientMap():HashMap<String,String?> {
    val patientMap = hashMapOf(
        "id" to id,
        "name" to name,
        "profilephoto" to profilephoto
    )
    return patientMap
}