package com.padcx.shared.data.models

import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PrescriptionVO
import com.padcx.shared.network.FirebaseApi

interface MedicineGuideModel {
    var mFirebaseApi : FirebaseApi
    fun finishConsult(doctor:DoctorVO,patientName:String,onSuccess:()->Unit,onFailure:(String)->Unit)
    fun savePrescription(prescription:List<PrescriptionVO>,onSuccess: () -> Unit,onFailure: (String) -> Unit)
}