package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ServerTimestamp
import com.padcx.shared.persistence.typeconverters.DateTypeConverter
import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap

@Entity(tableName = "messages")
@TypeConverters(DateTypeConverter::class)
data class MessageVO(
    @PrimaryKey
    var id:String = "",
    var text:String? = "",
    var image:String? = "",
    var sender:String? = "",
    var time:String? = "",
    var date:String? = "",
    @ServerTimestamp
    var timestamp:Date = Date()
):Serializable
fun MessageVO.toMessageMap(): HashMap<String, Any?> {
    val messageMap = hashMapOf(
        "id" to id,
        "text" to text,
        "image" to image,
        "sender" to sender,
        "time" to time,
        "date" to date,
        "timestamp" to FieldValue.serverTimestamp()
    )
    return messageMap
}