package com.padcx.shared.data.models.impls

import androidx.lifecycle.LiveData
import com.padcx.shared.data.models.BaseModel
import com.padcx.shared.data.models.MessageModel
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi

object MessageModelImpl:MessageModel,BaseModel() {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl
    override fun getConsultation(
        onSuccess: (List<ConsultVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConsultations(
            onSuccess = {
                mTheDB.consultDao().deleteConsultations()
                mTheDB.consultDao().insertConsultations(it)
                onSuccess(it)
            },
            onFailure = {
                onFailure(it)
            })
    }

    override fun getConsultByPatientIdFromDb(patientId: String): LiveData<List<ConsultVO>> {
        return mTheDB.consultDao().getConsultationByPatientId(patientId)
    }
}