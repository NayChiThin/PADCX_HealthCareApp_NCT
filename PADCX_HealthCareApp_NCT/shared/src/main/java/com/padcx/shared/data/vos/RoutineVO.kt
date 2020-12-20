package com.padcx.shared.data.vos

import java.io.Serializable

data class RoutineVO(
    var day:String? = null,
    var time:String? = null,
    var times_per_day:Int? = null,
    var total_days:Int? = null
):Serializable
fun RoutineVO.toRoutineMap() : HashMap<String,Any?> {
    val routineMap = hashMapOf(
        "day" to day,
        "time" to time,
        "times_per_day" to times_per_day,
        "total_days" to total_days
    )
    return routineMap
}