package com.padcx.shared.data.models

import androidx.lifecycle.LiveData
import com.padcx.shared.data.vos.*
import com.padcx.shared.network.FirebaseApi

interface HomeModel {
    var mFirebaseApi : FirebaseApi
    fun getSpecialities(onSuccess:(specialities:List<SpecialityVO>)->Unit,onFailure:(String)->Unit)
    fun acceptRequest(consult:ConsultVO, onSuccess:()->Unit, onFailure:(String)->Unit)
    fun getRecentDoctors(patientId:String,onSuccess: (doctors:List<DoctorVO>) -> Unit,onFailure: (String) -> Unit)
    fun getSpecialitiesFromDb():LiveData<List<SpecialityVO>>
    fun getRecentDoctorsFromDb(patientId:String):LiveData<List<DoctorVO>>
    fun getPreviousConsultations(doctorId:String,onSuccess: (List<ConsultVO>) -> Unit,onFailure: (String) -> Unit)
    fun getPreviousConsultationsFromDb(doctorId: String):LiveData<List<ConsultVO>>
    fun getConsultRequest(onSuccess: (List<ConsultRequestVO>) -> Unit,onFailure: (String) -> Unit)
    fun getConsultRequestFromDb(speciaityName: String):LiveData<List<ConsultRequestVO>>
    fun getDoctorById(doctorId: String,onSuccess: (DoctorVO) -> Unit,onFailure: (String) -> Unit)
}