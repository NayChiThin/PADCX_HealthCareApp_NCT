package com.padcx.shared.data.models.impls

import androidx.lifecycle.LiveData
import com.padcx.shared.data.models.BaseModel
import com.padcx.shared.data.models.ConsultModel
import com.padcx.shared.data.vos.MedicineVO
import com.padcx.shared.data.vos.MessageVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi

object ConsultModelImpl : ConsultModel,BaseModel() {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl

    override fun getSpecialityQuestions(
        specialityName: String,
        onSuccess: (questionList: List<QuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getSpecialityQuestions(specialityName,
            onSuccess={
                mTheDB.questionDao().insertQuestions(it)
            },
            onFailure={
                onFailure(it)
            })
    }

    override fun getSpecialityMedicine(
        specialityName: String,
        onSuccess: (medicineList: List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getSpecialityMedicines(specialityName,
            onSuccess={
                mTheDB.medicineDao().insertMedicines(it)
            },
            onFailure = {
                onFailure(it)
            })
    }

    override fun saveMessage(
        message: MessageVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.saveMessage(message, onSuccess, onFailure)
    }

    override fun getMessages(onSuccess: (List<MessageVO>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getCurrentMessages(
            onSuccess = {
                mTheDB.messageDao().insertMessages(it)
            },
            onFailure = {
                onFailure(it)
            }
        )
    }

    override fun getSpecialityQuestionsFromDb(name:String): LiveData<List<QuestionVO>> {
        return mTheDB.specialityDao().getSpecialityQuestions(name)
    }

    override fun getSpecialityMedicineFromDb(name:String): LiveData<List<MedicineVO>> {
        return mTheDB.specialityDao().getSpecialityMedicines(name)
    }

    override fun getMessagesFromDb(): LiveData<List<MessageVO>> {
        return mTheDB.messageDao().getMessages()
    }
}