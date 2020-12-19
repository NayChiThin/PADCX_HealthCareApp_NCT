package com.padcx.shared.data.models.impls

import androidx.lifecycle.LiveData
import com.padcx.shared.data.models.BaseModel
import com.padcx.shared.data.models.RegisterModel
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi


object RegisterModelImpl : RegisterModel,BaseModel() {

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

    override fun getGeneralQuestions(
        onSuccess: (questions: List<QuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getGeneralQuestions(
            onSuccess = {
                mTheDB.questionDao().deleteQuestions()
                mTheDB.questionDao().insertQuestions(it)
            },
            onFailure = {
                onFailure(it)
            }
        )
    }

    override fun getGeneralQuestionsFromDb(): LiveData<List<QuestionVO>> {
        return mTheDB.questionDao().getGeneralQuestions()
    }
}