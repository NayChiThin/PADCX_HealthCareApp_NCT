package com.padcx.shared.data.vos

import androidx.room.Embedded

data class PrescriptionVO (
    var id:String? = null,
    var count:Int? = null,
    @Embedded
    var medicine:MedicineVO? = null,
    @Embedded
    var routine:RoutineVO? = null
)
fun PrescriptionVO.toPrescriptionMap():HashMap<String,Any?> {
    val prescriptionMap = hashMapOf(
        "id" to id,
        "count" to count,
        "medicine" to medicine,
        "routine" to routine
    )
    return prescriptionMap
}