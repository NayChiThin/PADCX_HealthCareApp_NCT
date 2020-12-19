package com.padcx.shared.data.models

import androidx.lifecycle.LiveData
import com.padcx.shared.data.vos.ConsultRequestVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.network.FirebaseApi

interface FillCaseSummaryModel{
    var mFirebaseApi : FirebaseApi
    fun getPatientGeneralQuestions(patientId:String,onSuccess:(questions:List<QuestionVO>)->Unit,onFailure:(String)->Unit)
    fun getSpecialityQuestions(specialityName:String,onSuccess: (questions: List<QuestionVO>) -> Unit,onFailure: (String) -> Unit)
    fun requestConsultation(consultRequest:ConsultRequestVO,onSuccess:()->Unit,onFailure: (String) -> Unit)
    fun getPatientById(patientId: String,onSuccess: (patient:PatientVO) -> Unit,onFailure: (String) -> Unit)
    fun updateGeneralQuestionAnswer(patientId: String,question:QuestionVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)
}