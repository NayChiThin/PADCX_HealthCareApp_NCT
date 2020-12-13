package com.padcx.shared.data.models

import com.padcx.shared.data.vos.ConsultRequestVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.network.FirebaseApi

interface RequestConsultModel {
    var mFirebaseApi : FirebaseApi
    fun requestConsult(consultRequest:ConsultRequestVO, onSuccess:()->Unit,onFailure:(String)->Unit)
}