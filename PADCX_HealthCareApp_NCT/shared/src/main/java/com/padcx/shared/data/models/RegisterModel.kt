package com.padcx.shared.data.models

import androidx.lifecycle.LiveData
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.QuestionVO
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
    fun getGeneralQuestions(onSuccess: (questions:List<QuestionVO>) -> Unit, onFailure: (String) -> Unit)
    fun getGeneralQuestionsFromDb(): LiveData<List<QuestionVO>>
}