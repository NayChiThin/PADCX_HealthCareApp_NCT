package com.padcx.padcx_healthcareapp_nct.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.MessagePresenter
import com.padcx.padcx_healthcareapp_nct.mvp.views.MessageView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.MessageModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.MessageModelImpl
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class MessagePresenterImpl:MessagePresenter,AbstractBasePresenter<MessageView>() {
    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private var patientId = ""
    private val mMessageModel : MessageModel = MessageModelImpl
    private lateinit var lifecycleOwner : LifecycleOwner
    override fun onUiReady(owner: LifecycleOwner) {
        lifecycleOwner = owner
        patientId = mAuthenticationModel.getUserId()
        mMessageModel.getConsultation(
            onSuccess = {

            },
            onFailure = {
                mView.showError(it)
            }
        )
        mMessageModel.getConsultByPatientIdFromDb(patientId)
            .observe(lifecycleOwner, Observer {
                mView.displayConsultationHistory(it)
            })
    }


    override fun onTapSendMessage(consult: ConsultVO) {
        mView.navigateToChat(consult)
    }

    override fun onTapPrescription(consult: ConsultVO) {
        mView.displayPrescription(consult)
    }
}