package com.padcx.shared.data.models.impls

import com.padcx.shared.data.models.LoginModel
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi

object LoginModelImpl:LoginModel {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl

    override fun getDoctorById(
        doctorId: String,
        onSuccess: (DoctorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getDoctorById(doctorId,onSuccess,onFailure)
    }

    override fun getDoctorIdByPhoneNumber(
        phone: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getDoctorIdByPhoneNumber(phone,onSuccess, onFailure)
    }

    override fun getPatientById(
        patientId: String,
        onSuccess: (PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getPatientById(patientId,onSuccess,onFailure)
    }
}