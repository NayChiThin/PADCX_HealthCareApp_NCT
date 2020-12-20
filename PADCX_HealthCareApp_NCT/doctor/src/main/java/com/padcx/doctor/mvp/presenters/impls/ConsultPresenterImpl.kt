package com.padcx.doctor.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.doctor.mvp.presenters.ConsultPresenter
import com.padcx.doctor.mvp.views.ConsultView
import com.padcx.shared.data.models.ConsultModel
import com.padcx.shared.data.models.impls.ConsultModelImpl
import com.padcx.shared.data.vos.MedicineVO
import com.padcx.shared.data.vos.MessageVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter
/*

class ConsultPresenterImpl: ConsultPresenter,AbstractBasePresenter<ConsultView>() {

    private val mConsultModel : ConsultModel = ConsultModelImpl
    private lateinit var lifecycleOwner: LifecycleOwner

    override fun onUiReady(specialityName: String,lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
        loadDataFromApi(specialityName)
    }

    private fun loadDataFromApi(specialityName: String) {
        mConsultModel.getSpecialityQuestions(specialityName,
            onSuccess={
            },
            onFailure = {
            })
        mConsultModel.getSpecialityMedicine(specialityName,
            onSuccess={
            },
            onFailure = {
            })
    }

    override fun onTapGetQuestions(specialityName: String) {
        mConsultModel.getSpecialityQuestionsFromDb(specialityName)
            .observe(lifecycleOwner, Observer {
                it?.let {
                    // show questions
                }
            })
    }

    override fun onTapGetMedicines(specialityName: String) {
        mConsultModel.getSpecialityMedicineFromDb(specialityName)
            .observe(lifecycleOwner, Observer {
                it?.let {
                    // show medicine
                }
            })
    }

    override fun onTapSendMessage(message: MessageVO) {
        mConsultModel.saveMessage(message,
        onSuccess={
            Log.d("Success","Sent message")
        },
        onFailure = {
            mView.showError(it)
        })
    }
}*/
