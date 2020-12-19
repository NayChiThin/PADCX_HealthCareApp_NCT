package com.padcx.shared.data.models

import androidx.lifecycle.LiveData
import com.padcx.shared.data.vos.*
import com.padcx.shared.network.FirebaseApi

interface PatientDetailModel {
    var mFirebaseApi : FirebaseApi
    fun getDoctorById(doctorId:String,onSuccess:(DoctorVO)->Unit,onFailure:(String)->Unit)
    fun getDoctorFromDb(doctorId: String):LiveData<DoctorVO>
    fun createConsultation(consult:ConsultVO,onSuccess:()->Unit,onFailure: (String) -> Unit)
    fun updateRequestStatus(request:ConsultRequestVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)
}