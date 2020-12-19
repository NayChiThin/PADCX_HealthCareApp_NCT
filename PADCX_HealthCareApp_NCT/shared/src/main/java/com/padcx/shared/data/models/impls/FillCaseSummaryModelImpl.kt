package com.padcx.shared.data.models.impls

import androidx.lifecycle.LiveData
import com.padcx.shared.data.models.BaseModel
import com.padcx.shared.data.models.FillCaseSummaryModel
import com.padcx.shared.data.vos.ConsultRequestVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi

object FillCaseSummaryModelImpl :FillCaseSummaryModel,BaseModel(){
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl
    override fun getPatientGeneralQuestions(
        patientId: String,
        onSuccess: (questions: List<QuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getPatientById(patientId,
            onSuccess = {
            onSuccess(it.generalQuestions?: arrayListOf())
        }, onFailure = {
                onFailure(it)
            })
    }

    override fun getSpecialityQuestions(
        specialityName: String,
        onSuccess: (questions: List<QuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getSpecialityQuestions(specialityName, onSuccess, onFailure)
    }

    override fun requestConsultation(
        consultRequest: ConsultRequestVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.broadcastConsultRequest(consultRequest, onSuccess, onFailure)
    }

    override fun getPatientById(
        patientId: String,
        onSuccess: (patient: PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getPatientById(patientId,onSuccess, onFailure)
    }

    override fun updateGeneralQuestionAnswer(
        patientId: String,
        question: QuestionVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.updateGeneralQuestionAnswer(patientId,question,onSuccess,onFailure)
    }


}