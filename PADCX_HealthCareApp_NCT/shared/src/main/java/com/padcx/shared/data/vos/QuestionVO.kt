package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "questions")
data class QuestionVO(
    @SerializedName("sentence") var sentence:String? = null,
    @SerializedName("type") var type:String? = null,
    @SerializedName("ask_time") var ask_time:String? = null,
    @SerializedName("answer") var answer:String? = null,
    @PrimaryKey
    @SerializedName("name") var name:String = ""
)
fun QuestionVO.toQuestionMap(): HashMap<String, String?> {
    val questionMap = hashMapOf(
        "sentence" to sentence,
        "type" to type,
        "ask_time" to ask_time,
        "answer" to answer,
        "name" to name
    )
    return questionMap
}