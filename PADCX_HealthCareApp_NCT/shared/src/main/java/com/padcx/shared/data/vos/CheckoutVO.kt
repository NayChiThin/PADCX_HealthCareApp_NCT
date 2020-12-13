package com.padcx.shared.data.vos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.padcx.shared.persistence.typeconverters.PrescriptionVOListTypeConverter

@Entity(tableName = "checkout")
@TypeConverters(PrescriptionVOListTypeConverter::class)
data class CheckoutVO (
        @PrimaryKey
        var id : String? = null,
        var prescription:List<PrescriptionVO>? = null,
        var deliverAddress:String? = null,
        @Embedded
        var deliverRoutine:DeliverRoutineVO? = null,
        @Embedded(prefix = "patient_")
        var patient : PatientVO? = null,
        var totalCost:Float? = null
)
fun CheckoutVO.toCheckoutMap():HashMap<String,Any?> {
        val checkoutMap = hashMapOf(
                "id" to id,
                "deliver_address" to deliverAddress,
                "deliver_routine" to deliverRoutine,
                "total_cost" to totalCost
        )
        return checkoutMap
}