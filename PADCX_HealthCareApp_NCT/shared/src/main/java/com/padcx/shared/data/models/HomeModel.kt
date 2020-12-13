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
}