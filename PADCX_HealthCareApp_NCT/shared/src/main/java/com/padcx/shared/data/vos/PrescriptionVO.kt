package com.padcx.shared.data.vos

import androidx.room.Embedded
import java.io.Serializable

data class PrescriptionVO (
    var id:String? = null,
    var count:Int? = null,
    @Embedded
    var medicine:MedicineVO? = null,
    @Embedded
    var routine:RoutineVO? = null
):Serializable
fun PrescriptionVO.toPrescriptionMap(): HashMap<String, Serializable?> {
    val prescriptionMap = hashMapOf(
        "id" to id,
        "count" to count,
        "medicine" to medicine,
        "routine" to routine
    )
    return prescriptionMap
}