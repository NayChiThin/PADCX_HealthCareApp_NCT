package com.padcx.shared.data.models

import androidx.lifecycle.LiveData
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.network.FirebaseApi

interface MessageModel{

    var mFirebaseApi : FirebaseApi
    fun getConsultation(onSuccess: (List<ConsultVO>) -> Unit, onFailure: (String) -> Unit)
    fun getConsultByPatientIdFromDb(patientId: String): LiveData<List<ConsultVO>>
}