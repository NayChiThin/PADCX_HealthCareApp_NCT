package com.padcx.shared.data.vos

data class DeliverRoutineVO(
        var date:String? = null,
        var time:String? = null
)
fun DeliverRoutineVO.toDeliverRoutineMap():HashMap<String,String?> {
        val deliverRoutineMap = hashMapOf(
                "date" to date,
                "time" to time
        )
        return deliverRoutineMap
}