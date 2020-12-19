package com.padcx.shared.data.models.impls

import androidx.lifecycle.LiveData
import com.padcx.shared.data.models.BaseModel
import com.padcx.shared.data.models.PatientDetailModel
import com.padcx.shared.data.vos.*
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi

object PatientDetailModelImpl: PatientDetailModel,BaseModel() {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl
    override fun getDoctorById(
        doctorId: String,
        onSuccess: (DoctorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getDoctorById(doctorId,
            onSuccess = {
                mTheDB.doctorDao().deleteDoctors()
                mTheDB.doctorDao().insertDoctor(it)
                onSuccess(it)
            },
            onFailure = {
                onFailure(it)
            })
    }

    override fun getDoctorFromDb(doctorId: String): LiveData<DoctorVO> {
        return mTheDB.doctorDao().getDoctorById(doctorId)
    }

    override fun createConsultation(
        consult: ConsultVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.createConsultation(consult,onSuccess, onFailure)
    }

    override fun updateRequestStatus(
        request: ConsultRequestVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.updateRequestStatus(request,onSuccess, onFailure)
    }

}