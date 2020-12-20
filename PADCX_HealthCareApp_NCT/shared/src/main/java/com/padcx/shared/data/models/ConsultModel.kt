package com.padcx.shared.data.models

import androidx.lifecycle.LiveData
import com.padcx.shared.data.vos.MedicineVO
import com.padcx.shared.data.vos.MessageVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.network.FirebaseApi

interface ConsultModel {
    var mFirebaseApi : FirebaseApi
    fun getSpecialityQuestions(specialityName:String,onSuccess:(questionList:List<QuestionVO>)->Unit,onFailure:(String)->Unit)
    fun getSpecialityMedicine(specialityName: String,onSuccess:(medicineList:List<MedicineVO>)->Unit,onFailure: (String) -> Unit)
//    fun saveMessage(message:MessageVO,onSuccess:()->Unit,onFailure: (String) -> Unit)
    fun getMessages(consultId:String,onSuccess: (List<MessageVO>) -> Unit,onFailure: (String) -> Unit)
    fun getSpecialityQuestionsFromDb(name:String):LiveData<List<QuestionVO>>
    fun getSpecialityMedicineFromDb(name:String):LiveData<List<MedicineVO>>
    fun getMessagesFromDb():LiveData<List<MessageVO>>
}