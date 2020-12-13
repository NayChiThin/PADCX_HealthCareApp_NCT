package com.padcx.shared.data.models.impls

import com.padcx.shared.data.models.RegisterModel
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi


object RegisterModelImpl : RegisterModel {

    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl

    override fun registerNewDoctor(
        doctor: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.registerNewDoctor(doctor,onSuccess,onFailure)
    }

    override fun registerNewPatient(patient: PatientVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.registerNewPatient(patient, onSuccess, onFailure)
    }

}