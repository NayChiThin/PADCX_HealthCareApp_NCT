package com.padcx.shared.data.models.impls

import androidx.lifecycle.LiveData
import com.padcx.shared.data.models.BaseModel
import com.padcx.shared.data.models.ChatModel
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.data.vos.MessageVO
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi

object ChatModelImpl:ChatModel,BaseModel() {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl

    override fun saveMessages(
        consultId:String,
        message: MessageVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.saveMessage(consultId,message,
            onSuccess,onFailure)
    }

    override fun getMessages(
        consultId: String,
        onSuccess: (List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getCurrentMessages(consultId,
            onSuccess = {
                mTheDB.messageDao().deleteMessages()
                mTheDB.messageDao().insertMessages(it)
                onSuccess(it)
            },
            onFailure = {
                onFailure(it)
            })
    }

    override fun getMessagesFromDb(): LiveData<List<MessageVO>> {
        return mTheDB.messageDao().getMessages()
    }
}