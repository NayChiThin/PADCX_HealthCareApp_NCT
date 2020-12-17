package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.padcx.shared.persistence.typeconverters.AddressVOListTypeConverter
import com.padcx.shared.persistence.typeconverters.DoctorVOListTypeConverter
import com.padcx.shared.persistence.typeconverters.QuestionVOListTypeConverter

@Entity(tableName = "patients")
@TypeConverters(AddressVOListTypeConverter::class,DoctorVOListTypeConverter::class,QuestionVOListTypeConverter::class)
data class PatientVO(
    @PrimaryKey
    var id : String = "",
    var name:String? = null,
    var address:List<AddressVO>? = null,
    var profilephoto:String? = null,
    var recentDoctors:List<DoctorVO>? = null,
    var generalQuestions:List<QuestionVO>? = null
)
fun PatientVO.toPatientMap():HashMap<String,String?> {
    val patientMap = hashMapOf(
        "id" to id,
        "name" to name,
        "profilephoto" to profilephoto
    )
    return patientMap
}