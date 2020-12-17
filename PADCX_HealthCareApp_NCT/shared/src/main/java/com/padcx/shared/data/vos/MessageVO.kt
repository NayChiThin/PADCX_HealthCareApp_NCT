package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageVO(
    @PrimaryKey
    var id:String = "",
    var text:String? = null,
    var image:String? = null,
    var sender:String? = null,
    var time:String? = null,
    var date:String? = null
)
fun MessageVO.toMessageMap():HashMap<String,String?> {
    val messageMap = hashMapOf(
        "id" to id,
        "text" to text,
        "image" to image,
        "sender" to sender,
        "time" to time,
        "date" to date
    )
    return messageMap
}