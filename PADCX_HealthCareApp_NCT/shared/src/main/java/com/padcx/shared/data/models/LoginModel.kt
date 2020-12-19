package com.padcx.shared.data.models

import androidx.lifecycle.LiveData
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.network.FirebaseApi

interface LoginModel {
    var mFirebaseApi : FirebaseApi
    fun getDoctorById(doctorId:String,onSuccess:(DoctorVO)->Unit,onFailure:(String)->Unit)
    fun getDoctorIdByPhoneNumber(phone:String,onSuccess: (String) -> Unit,onFailure: (String) -> Unit)
    fun getPatientById(patientId:String,onSuccess: (PatientVO) -> Unit,onFailure: (String) -> Unit)
}