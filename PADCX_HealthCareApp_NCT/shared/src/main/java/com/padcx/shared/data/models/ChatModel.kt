package com.padcx.shared.data.models

import androidx.lifecycle.LiveData
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.data.vos.MessageVO
import com.padcx.shared.network.FirebaseApi

interface ChatModel {
    var mFirebaseApi : FirebaseApi
    fun saveMessages(consultId:String,message:MessageVO,onSuccess:()->Unit,onFailure:(String)->Unit)
    fun getMessages(consultId: String,onSuccess: (List<MessageVO>) -> Unit,onFailure: (String) -> Unit)
    fun getMessagesFromDb():LiveData<List<MessageVO>>
}