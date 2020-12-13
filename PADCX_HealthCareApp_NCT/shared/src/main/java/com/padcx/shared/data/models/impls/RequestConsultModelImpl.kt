package com.padcx.shared.data.models.impls

import com.padcx.shared.data.models.RequestConsultModel
import com.padcx.shared.data.vos.ConsultRequestVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi

object RequestConsultModelImpl:
    RequestConsultModel {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl
    override fun requestConsult(
        consultRequest:ConsultRequestVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.broadcastConsultRequest(consultRequest, onSuccess, onFailure)
    }
}