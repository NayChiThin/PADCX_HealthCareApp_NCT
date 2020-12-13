package com.padcx.shared.data.models

import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.network.FirebaseApi

interface RegisterModel {

    var mFirebaseApi : FirebaseApi

    fun registerNewDoctor(
        doctor: DoctorVO,
        onSuccess:()->Unit, onFailure:(String)->Unit
    )
    fun registerNewPatient(
        patient:PatientVO,
        onSuccess: () -> Unit,onFailure: (String) -> Unit
    )
}