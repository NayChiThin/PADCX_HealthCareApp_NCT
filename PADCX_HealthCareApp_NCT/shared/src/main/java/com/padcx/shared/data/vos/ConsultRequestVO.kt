package com.padcx.shared.data.vos

import androidx.room.*
import com.padcx.shared.persistence.typeconverters.QuestionVOListTypeConverter
import java.io.Serializable

@Entity(tableName = "consult_request")
@TypeConverters(QuestionVOListTypeConverter::class)
data class ConsultRequestVO (
    @PrimaryKey()
    var id : String = "",
    var caseSummary : List<QuestionVO>? = null,
    @Embedded(prefix = "patient_")
    var patient : PatientVO? = null,
    var speciality : String? = null,
    var status : String? = null
):Serializable
fun ConsultRequestVO.toConsultRequestMap():HashMap<String,String?> {
    val consultResultMap = hashMapOf(
        "id" to id,
        "speciality" to speciality,
        "status" to status
    )
    return consultResultMap
}