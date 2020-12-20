package com.padcx.shared.data.vos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.padcx.shared.persistence.typeconverters.MessageVOListTypeConverter
import com.padcx.shared.persistence.typeconverters.PrescriptionVOListTypeConverter
import com.padcx.shared.persistence.typeconverters.QuestionVOListTypeConverter
import java.io.Serializable

@Entity(tableName = "consultation")
@TypeConverters(QuestionVOListTypeConverter::class,MessageVOListTypeConverter::class,PrescriptionVOListTypeConverter::class)
data class ConsultVO (
    @PrimaryKey
    var id:String = "",
    var caseSummary: List<QuestionVO>? = null,
    var prescription: List<PrescriptionVO>? = null,
    var messages: List<MessageVO>? = null,
    @Embedded(prefix = "doctor_")
    var doctor : DoctorVO? = null,
    @Embedded(prefix = "patient_")
    var patient : PatientVO? = null
):Serializable
fun ConsultVO.toConsultMap():HashMap<String,String> {
    val consultMap = hashMapOf(
        "id" to id
    )
    return consultMap
}